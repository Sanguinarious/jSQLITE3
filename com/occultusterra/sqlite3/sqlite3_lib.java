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

import com.sun.jna.*;
import com.sun.jna.ptr.*;

public interface sqlite3_lib extends Library {
	/* Open Stuff */
	int sqlite3_open(String filename, PointerByReference ppDb);
	int sqlite3_open16(WString filename, PointerByReference ppDb);
	int sqlite3_open_v2(String filename, PointerByReference ppDb,int flags, String zVfs);
	/* Close Stuff */
	int sqlite3_close(Pointer handle);
	int sqlite3_close_v2(Pointer handle);
	/* Prepare Stuff */
	int sqlite3_prepare(Pointer handle, String zSql, int nByte, PointerByReference stmt_handle, PointerByReference pzTail);
	int sqlite3_prepare_v2(Pointer handle, String zSql, int nByte, PointerByReference stmt_handle, PointerByReference pzTail);
	int sqlite3_prepare16(Pointer handle, WString zSql, int nByte, PointerByReference stmt_handle, PointerByReference pzTail);
	int sqlite3_prepare16_v2(Pointer handle, WString zSql, int nByte, PointerByReference stmt_handle, PointerByReference pzTail);
	/* Binding Stuff */
	int sqlite3_bind_blob(Pointer stmt_handle, int col, Pointer data, int len, Callback c);
	int sqlite3_bind_blob(Pointer stmt_handle, int col, Pointer data, int len, int callback_c);
	int sqlite3_bind_blob64(Pointer stmt_handle, int col, Pointer data, long len, Callback c);
	int sqlite3_bind_blob64(Pointer stmt_handle, int col, Pointer data, long len, int callback_c);
	int sqlite3_bind_double(Pointer stmt_handle, int col, double d);
	int sqlite3_bind_int(Pointer stmt_handle, int col, int i);
	int sqlite3_bind_int64(Pointer stmt_handle, int col, long i64);
	int sqlite3_bind_null(Pointer stmt_handle, int col);
	int sqlite3_bind_text(Pointer stmt_handle, int col, String txt, int len, Callback c);
	int sqlite3_bind_text(Pointer stmt_handle, int col, String txt, int len, int callback_c);
	int sqlite3_bind_text16(Pointer stmt_handle, int col, WString txt, int len, Callback c);
	int sqlite3_bind_text16(Pointer stmt_handle, int col, WString txt, int len, int callback_c);
	int sqlite3_bind_text64(Pointer stmt_handle, int col, Pointer data, long len, Callback c, int encoding);
	int sqlite3_bind_text64(Pointer stmt_handle, int col, Pointer data, long len, int callback_c, int encoding);
	int sqlite3_bind_zeroblob(Pointer stmt_handle, int col, int len);
	int sqlite3_bind_zeroblob64(Pointer stmt_handle, int col, long len);
	/* Results */
	Pointer sqlite3_column_blob(Pointer sqlite3_stmt, int iCol);
	int sqlite3_column_bytes(Pointer sqlite3_stmt, int iCol);
	int sqlite3_column_bytes16(Pointer sqlite3_stmt, int iCol);
	double sqlite3_column_double(Pointer sqlite3_stmt, int iCol);
	int sqlite3_column_int(Pointer sqlite3_stmt, int iCol);
	long sqlite3_column_int64(Pointer sqlite3_stmt, int iCol);
	String sqlite3_column_text(Pointer sqlite3_stmt, int iCol);
	WString sqlite3_column_text16(Pointer sqlite3_stmt, int iCol);
	int sqlite3_column_type(Pointer sqlite3_stmt, int iCol);
	/* Finishing */
	int sqlite3_finalize(Pointer sqlite3_stmt);
	/* Misc Binding Stuff */
	String sqlite3_sql(Pointer stmt_handle);
	boolean sqlite3_stmt_readonly(Pointer stmt_handle);
	boolean sqlite3_stmt_busy(Pointer stmt_handle);
	int sqlite3_bind_parameter_count(Pointer stmt_handle);
	int sqlite3_clear_bindings(Pointer stmt_handle);
	int sqlite3_column_count(Pointer stmt_handle);
	int sqlite3_bind_parameter_index(Pointer stmt_handle, String zName);
	int sqlite3_data_count(Pointer stmt_handle);
	int sqlite3_reset(Pointer stmt_handle);
	int sqlite3_db_readonly(Pointer stmt_handle, String zDbName);
	Pointer sqlite3_next_stmt(Pointer handle, Pointer stmt_handle);
	/* Value Fun Functions */
	int sqlite3_bind_value(Pointer stmt_handle, int iCol, Pointer sqlite3_value);
	Pointer sqlite3_column_value(Pointer stmt_handle, int iCol);
	Pointer sqlite3_value_dup(Pointer sqlite3_value); // Unprotected -> Protected
	void sqlite3_value_free(Pointer sqlite3_value); // Free Protected from ^
	Pointer sqlite3_value_blob(Pointer sqlite3_value);
	int sqlite3_value_bytes(Pointer sqlite3_value);
	int sqlite3_value_bytes16(Pointer sqlite3_value);
	double sqlite3_value_double(Pointer sqlite3_value);
	int sqlite3_value_int(Pointer sqlite3_value);
	long sqlite3_value_int64(Pointer sqlite3_value);
	String sqlite3_value_text(Pointer sqlite3_value);
	WString sqlite3_value_text16(Pointer sqlite3_value);
	WString sqlite3_value_text16le(Pointer sqlite3_value);
	WString sqlite3_value_text16be(Pointer sqlite3_value);
	int sqlite3_value_type(Pointer sqlite3_value);
	int sqlite3_value_numeric_type(Pointer sqlite3_value);
	/* Misc Column Stuff */
	String sqlite3_column_name(Pointer stmt_handle, int N);
	WString sqlite3_column_name16(Pointer stmt_handle, int N);
	String sqlite3_column_database_name(Pointer stmt_handle,int N);
	WString sqlite3_column_database_name16(Pointer stmt_handle,int N);
	String sqlite3_column_table_name(Pointer stmt_handle,int N);
	WString sqlite3_column_table_name16(Pointer stmt_handle,int N);
	String sqlite3_column_origin_name(Pointer stmt_handle,int N);
	WString sqlite3_column_origin_name16(Pointer stmt_handle,int N);
	/* Executing Compiled Statements */
	int sqlite3_step(Pointer stmt_handle);
	/* Error Codes */
	int sqlite3_errcode(Pointer handle);
	int sqlite3_extended_errcode(Pointer handle);
	String sqlite3_errmsg(Pointer handle);
	WString sqlite3_errmsg16(Pointer handle);
	String sqlite3_errstr(int error);
	int sqlite3_extended_result_codes(Pointer handle, boolean onoff);
	/* Misc Things */
	int sqlite3_changes(Pointer handle);
	int sqlite3_total_changes(Pointer handle);
	long sqlite3_last_insert_rowid(Pointer handle);
	void sqlite3_interrupt(Pointer handle);
	void sqlite3_progress_handler(Pointer handle, int numinstructions, Callback c, Object callback_data);
	int sqlite3_complete(String sql);
	int sqlite3_complete16(WString sql);
	int sqlite3_busy_timeout(Pointer handle, int ms);
	int sqlite3_get_autocommit(Pointer handle);
	/* Library Checks + Misc */
	String sqlite3_libversion();
	String sqlite3_sourceid();
	int sqlite3_compileoption_used(String zOptName);
	String sqlite3_compileoption_get(int N);
	int sqlite3_threadsafe();
	long sqlite3_memory_used();
	/* Mutex Handling */
	Pointer sqlite3_mutex_alloc(int param);
	void sqlite3_mutex_free(Pointer sqlite3_mutex);
	void sqlite3_mutex_enter(Pointer sqlite3_mutex);
	int sqlite3_mutex_try(Pointer sqlite3_mutex);
	void sqlite3_mutex_leave(Pointer sqlite3_mutex);
	/* Callback Handlers */
	interface sqlite3_progress extends Callback {
		int callback(Object callback_data);
	}
	interface sqlite3_data_destructor extends Callback {
		int callback(Object callback_data);
	}
}
