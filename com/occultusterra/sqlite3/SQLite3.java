/*  
  Copyright (C) 2016 William Welna (wwelna@occultusterra.com)
  
  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:

  The above copyright notice and this permission notice shall be included in
  all copies or substantial portions of the Software.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
  THE SOFTWARE.
*/

package com.occultusterra.sqlite3;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public class SQLite3 implements AutoCloseable {
	sqlite3_lib sqlite = (sqlite3_lib) Native.loadLibrary("sqlite3", sqlite3_lib.class);
	Pointer sqlite3_handle = Pointer.NULL;
	Pointer sqlite3_mutex = Pointer.NULL;
	int err = 0;
	
	public SQLite3(String dbname) throws Exception {
		open(dbname);
	}
	
	public void open(String dbname) throws Exception {
		if(sqlite3_handle != Pointer.NULL) // If it is open, throw
			throw new Exception("SQLite3 DB Already Opened");
		PointerByReference psqlite3 = new PointerByReference();
		err = sqlite.sqlite3_open("test.db", psqlite3);
		if(sqlite3_errors.SQLITE_OK != err)
			throw new Exception(sqlite.sqlite3_errstr(err));
		sqlite3_handle = psqlite3.getValue();
		sqlite3_mutex = sqlite.sqlite3_mutex_alloc(sqlite3_params.SQLITE_MUTEX_FAST);
	}
	
	public void close() {
		if(sqlite3_handle != Pointer.NULL) {
			Pointer stmt = Pointer.NULL;
			do { // Attempt to finalize all statements before closing db
				stmt = sqlite.sqlite3_next_stmt(sqlite3_handle, stmt);
				sqlite.sqlite3_finalize(stmt);
			} while(stmt != Pointer.NULL);
			sqlite.sqlite3_close_v2(sqlite3_handle);
			sqlite3_handle = Pointer.NULL;
		}
		if(sqlite3_mutex != Pointer.NULL) {
			sqlite.sqlite3_mutex_free(sqlite3_mutex);
			sqlite3_mutex = Pointer.NULL;
		}
	}
	
	public int getLastError() {
		return err;
	}
	
	public int exec(String sql) throws Exception {
		PointerByReference psqlite3_stmt = new PointerByReference();
		PointerByReference nully = new PointerByReference();
		int steps=0;
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_enter(sqlite3_mutex);
		err = sqlite.sqlite3_prepare_v2(sqlite3_handle, sql, -1, psqlite3_stmt, nully);
		if(sqlite3_errors.SQLITE_OK != err) {
			if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
			throw new Exception(sqlite.sqlite3_errstr(err));
		}
		do ++steps; while (sqlite3_errors.SQLITE_DONE != sqlite.sqlite3_step(psqlite3_stmt.getValue()));
		sqlite.sqlite3_finalize(psqlite3_stmt.getValue());
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
		return steps;
	}
	
	public Stmt prepare(String sql) throws Exception {
		PointerByReference psqlite3_stmt = new PointerByReference();
		PointerByReference nully = new PointerByReference();
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_enter(sqlite3_mutex);
		err = sqlite.sqlite3_prepare_v2(sqlite3_handle, sql, -1, psqlite3_stmt, nully);
		if(sqlite3_errors.SQLITE_OK != err) {
			if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
			throw new Exception(sqlite.sqlite3_errstr(err));
		}
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
		return new Stmt(sqlite, psqlite3_stmt.getValue(), sql, sqlite3_handle, sqlite3_mutex);
	}
	
	public void begin() throws Exception {
		exec("BEGIN EXCLUSIVE;");
	}
	
	public void end() throws Exception {
		try { exec("COMMIT;");
		} catch(Exception e) {
			try { exec("ROLLBACK;");
			} catch(Exception e2) {}
			throw e;
		}
	}

}
