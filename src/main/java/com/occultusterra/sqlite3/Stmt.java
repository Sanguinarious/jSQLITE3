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

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.WString;

public class Stmt implements AutoCloseable {
	sqlite3_lib sqlite;
	Pointer sqlite3_stmt;
	Pointer sqlite3_handle;
	Pointer sqlite3_mutex;
	String sql;

	protected Stmt(sqlite3_lib sqlite, Pointer sqlite3_stmt, String sql, Pointer sqlite3_handle, Pointer sqlite3_mutex) {
		this.sqlite3_stmt = sqlite3_stmt;
		this.sqlite3_handle = sqlite3_handle;
		this.sqlite3_mutex = sqlite3_mutex;
		this.sqlite = sqlite;
		this.sql = sql;
	}
	
	public void bindInt(int column, int i) throws SQLite3Exception {
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_enter(sqlite3_mutex);
		int err = sqlite.sqlite3_bind_int(sqlite3_stmt, column, i);
		if(err != sqlite3_errors.SQLITE_OK) {
			if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
			throw new SQLite3Exception(sqlite, err, sql);
		}
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
	}
	
	public void bindLong(int column, long l) throws SQLite3Exception {
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_enter(sqlite3_mutex);
		int err = sqlite.sqlite3_bind_int64(sqlite3_stmt, column, l);
		if(err != sqlite3_errors.SQLITE_OK) {
			if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
			throw new SQLite3Exception(sqlite, err, sql);
		}
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
	}
	
	public void bindDouble(int column, double d) throws SQLite3Exception {
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_enter(sqlite3_mutex);
		int err = sqlite.sqlite3_bind_double(sqlite3_stmt, column, d);
		if(err != sqlite3_errors.SQLITE_OK) {
			if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
			throw new SQLite3Exception(sqlite, err, sql);
		}
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
	}
	
	public void bindText(int column, String s) throws SQLite3Exception {
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_enter(sqlite3_mutex);
		int err = sqlite.sqlite3_bind_text(sqlite3_stmt, column, s, -1, sqlite3_params.SQLITE_TRANSIENT);
		if(err != sqlite3_errors.SQLITE_OK) {
			if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
			throw new SQLite3Exception(sqlite, err, sql);
		}
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
	}
	
	public void bindText16(int column, String s) throws SQLite3Exception {
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_enter(sqlite3_mutex);
		int err = sqlite.sqlite3_bind_text16(sqlite3_stmt, column, new WString(s), -1, sqlite3_params.SQLITE_TRANSIENT);
		if(err != sqlite3_errors.SQLITE_OK) {
			if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
			throw new SQLite3Exception(sqlite, err, sql);
		}
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
	}
	
	public void bindBlob(int column, byte[] b) throws SQLite3Exception {
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_enter(sqlite3_mutex);
		Pointer in = new Memory(b.length);
		in.write(0, b, 0, b.length);
		int err = sqlite.sqlite3_bind_blob(sqlite3_stmt, column, in, b.length, sqlite3_params.SQLITE_TRANSIENT);
		if(err != sqlite3_errors.SQLITE_OK) {
			if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
			throw new SQLite3Exception(sqlite, err, sql);
		}
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
	}
	
	public void bindZeroBlob(int column, int len) throws SQLite3Exception {
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_enter(sqlite3_mutex);
		int err = sqlite.sqlite3_bind_zeroblob(sqlite3_stmt, column, len);
		if(err != sqlite3_errors.SQLITE_OK) {
			if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
			throw new SQLite3Exception(sqlite, err, sql);
		}
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
	}
	
	public void bindNull(int column) throws SQLite3Exception {
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_enter(sqlite3_mutex);
		int err = sqlite.sqlite3_bind_null(sqlite3_stmt, column);
		if(err != sqlite3_errors.SQLITE_OK) {
			if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
			throw new SQLite3Exception(sqlite, err, sql);
		}
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
	}
	
	public int columnInt(int column) throws SQLite3Exception {
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_enter(sqlite3_mutex);
		if(sqlite.sqlite3_column_type(sqlite3_stmt, column) != sqlite3_params.SQLITE_INTEGER)
			throw new SQLite3Exception("Stmt.columnInt expected INTEGER");
		int ret = sqlite.sqlite3_column_int(sqlite3_stmt, column);
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
		return ret;
	}
	
	public long columnLong(int column) throws SQLite3Exception {
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_enter(sqlite3_mutex);
		if(sqlite.sqlite3_column_type(sqlite3_stmt, column) != sqlite3_params.SQLITE_INTEGER)
			throw new SQLite3Exception("Stmt.columnLong expected INTEGER");
		long ret = sqlite.sqlite3_column_int64(sqlite3_stmt, column);
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
		return ret;
	}
	
	public Double columnDouble(int column) throws SQLite3Exception {
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_enter(sqlite3_mutex);
		if(sqlite.sqlite3_column_type(sqlite3_stmt, column) != sqlite3_params.SQLITE_FLOAT)
			throw new SQLite3Exception("Stmt.columnDouble expected FLOAT");
		double ret = sqlite.sqlite3_column_double(sqlite3_stmt, column);
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
		return ret;
	}
	
	public String columnString(int column) throws SQLite3Exception {
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_enter(sqlite3_mutex);
		if(sqlite.sqlite3_column_type(sqlite3_stmt, column) != sqlite3_params.SQLITE_TEXT)
			throw new SQLite3Exception("Stmt.columnString expected TEXT");
		String ret = sqlite.sqlite3_column_text(sqlite3_stmt, column);
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
		return ret;
	}
	
	public String columnString16(int column) throws SQLite3Exception {
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_enter(sqlite3_mutex);
		if(sqlite.sqlite3_column_type(sqlite3_stmt, column) != sqlite3_params.SQLITE_TEXT)
			throw new SQLite3Exception("Stmt.columnString expected TEXT");
		String ret = new String(sqlite.sqlite3_column_text16(sqlite3_stmt, column).toString());
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
		return ret;
	}
	
	public byte[] columnBlob(int column) throws SQLite3Exception {
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_enter(sqlite3_mutex);
		if(sqlite.sqlite3_column_type(sqlite3_stmt, column) != sqlite3_params.SQLITE_BLOB)
			throw new SQLite3Exception("Stmt.columnString expected BLOB");
		Pointer p = sqlite.sqlite3_column_blob(sqlite3_stmt,column);
		int len = sqlite.sqlite3_column_bytes(sqlite3_stmt, column);
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
		return p.getByteArray(0, len);
		
	}
	
	public void columnNull(int column) throws SQLite3Exception {
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_enter(sqlite3_mutex);
		if(sqlite.sqlite3_column_type(sqlite3_stmt, column) != sqlite3_params.SQLITE_NULL)
			throw new SQLite3Exception("Stmt.columnNull Expected NULL");
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
	}
	
	public String columnType(int column) {
		String ret;
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_enter(sqlite3_mutex);
		switch(sqlite.sqlite3_column_type(sqlite3_stmt, column)) {
			case sqlite3_params.SQLITE_INTEGER:
				ret = "INTEGER"; break;
			case sqlite3_params.SQLITE_FLOAT:
				ret = "FLOAT"; break;
			case sqlite3_params.SQLITE_TEXT:
				ret = "TEXT"; break;
			case sqlite3_params.SQLITE_BLOB:
				ret = "BLOB"; break;
			case sqlite3_params.SQLITE_NULL:
				ret = "NULL"; break;
			default:
				ret = "UNDEFINED";
		}
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);	
		return ret;
	}
	
	public boolean step() throws SQLite3Exception {
		int err = sqlite.sqlite3_step(sqlite3_stmt);
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_enter(sqlite3_mutex);
		if(err == sqlite3_errors.SQLITE_ROW) {
			if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
			return true;
		} else if(err == sqlite3_errors.SQLITE_DONE) {
			if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
			return false;
		} else {
			if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
			throw new SQLite3Exception(sqlite, err, sql);
		}
	}

	public void reset() throws SQLite3Exception {
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_enter(sqlite3_mutex);
		int err = sqlite.sqlite3_reset(sqlite3_stmt);
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
		if(err != sqlite3_errors.SQLITE_OK)
			throw new SQLite3Exception(sqlite, err, sql);
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_enter(sqlite3_mutex);
		err = sqlite.sqlite3_clear_bindings(sqlite3_stmt);
		if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
		if(err != sqlite3_errors.SQLITE_OK)
			throw new SQLite3Exception(sqlite, err, sql);
	}
	
	@Override public void close() throws SQLite3Exception {
		int err;
		if(sqlite3_stmt != Pointer.NULL) {
			if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_enter(sqlite3_mutex);
			if((err=sqlite.sqlite3_finalize(sqlite3_stmt)) != sqlite3_errors.SQLITE_OK) {
				if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
				throw new SQLite3Exception(sqlite, err, sql);
			}
			if(sqlite3_mutex != Pointer.NULL) sqlite.sqlite3_mutex_leave(sqlite3_mutex);
		}
	}
}
