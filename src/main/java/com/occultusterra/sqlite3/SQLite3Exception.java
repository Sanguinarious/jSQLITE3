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

public class SQLite3Exception extends Exception {
	private static final long serialVersionUID = 4646472904717584212L;

	public SQLite3Exception(String reason) {
		super(reason);
	}
	
	SQLite3Exception(sqlite3_lib sqlite, int err) {
		super(sqlite.sqlite3_errstr(err));
	}
	
	SQLite3Exception(sqlite3_lib sqlite, int err, String sql) {
		super(sqlite.sqlite3_errstr(err)+" :> "+sql);
	}

}
