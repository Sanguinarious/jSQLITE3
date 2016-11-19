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

public class sqlite3_params {
	
	public final static int SQLITE_OPEN_READONLY         =0x00000001;  /* Ok for sqlite3_open_v2() */
	public final static int SQLITE_OPEN_READWRITE        =0x00000002;  /* Ok for sqlite3_open_v2() */
	public final static int SQLITE_OPEN_CREATE           =0x00000004;  /* Ok for sqlite3_open_v2() */
	public final static int SQLITE_OPEN_DELETEONCLOSE    =0x00000008;  /* VFS only */
	public final static int SQLITE_OPEN_EXCLUSIVE        =0x00000010;  /* VFS only */
	public final static int SQLITE_OPEN_AUTOPROXY        =0x00000020;  /* VFS only */
	public final static int SQLITE_OPEN_URI              =0x00000040;  /* Ok for sqlite3_open_v2() */
	public final static int SQLITE_OPEN_MEMORY           =0x00000080;  /* Ok for sqlite3_open_v2() */
	public final static int SQLITE_OPEN_MAIN_DB          =0x00000100;  /* VFS only */
	public final static int SQLITE_OPEN_TEMP_DB          =0x00000200;  /* VFS only */
	public final static int SQLITE_OPEN_TRANSIENT_DB     =0x00000400;  /* VFS only */
	public final static int SQLITE_OPEN_MAIN_JOURNAL     =0x00000800;  /* VFS only */
	public final static int SQLITE_OPEN_TEMP_JOURNAL     =0x00001000;  /* VFS only */
	public final static int SQLITE_OPEN_SUBJOURNAL       =0x00002000;  /* VFS only */
	public final static int SQLITE_OPEN_MASTER_JOURNAL   =0x00004000;  /* VFS only */
	public final static int SQLITE_OPEN_NOMUTEX          =0x00008000;  /* Ok for sqlite3_open_v2() */
	public final static int SQLITE_OPEN_FULLMUTEX        =0x00010000;  /* Ok for sqlite3_open_v2() */
	public final static int SQLITE_OPEN_SHAREDCACHE      =0x00020000;  /* Ok for sqlite3_open_v2() */
	public final static int SQLITE_OPEN_PRIVATECACHE     =0x00040000;  /* Ok for sqlite3_open_v2() */
	public final static int SQLITE_OPEN_WAL              =0x00080000;  /* VFS only */

	public final static int SQLITE_IOCAP_ATOMIC                 =0x00000001;
	public final static int SQLITE_IOCAP_ATOMIC512              =0x00000002;
	public final static int SQLITE_IOCAP_ATOMIC1K               =0x00000004;
	public final static int SQLITE_IOCAP_ATOMIC2K               =0x00000008;
	public final static int SQLITE_IOCAP_ATOMIC4K               =0x00000010;
	public final static int SQLITE_IOCAP_ATOMIC8K               =0x00000020;
	public final static int SQLITE_IOCAP_ATOMIC16K              =0x00000040;
	public final static int SQLITE_IOCAP_ATOMIC32K              =0x00000080;
	public final static int SQLITE_IOCAP_ATOMIC64K              =0x00000100;
	public final static int SQLITE_IOCAP_SAFE_APPEND            =0x00000200;
	public final static int SQLITE_IOCAP_SEQUENTIAL             =0x00000400;
	public final static int SQLITE_IOCAP_UNDELETABLE_WHEN_OPEN  =0x00000800;
	public final static int SQLITE_IOCAP_POWERSAFE_OVERWRITE    =0x00001000;
	public final static int SQLITE_IOCAP_IMMUTABLE              =0x00002000;
	
	public final static int SQLITE_LOCK_NONE          =0;
	public final static int SQLITE_LOCK_SHARED        =1;
	public final static int SQLITE_LOCK_RESERVED      =2;
	public final static int SQLITE_LOCK_PENDING       =3;
	public final static int SQLITE_LOCK_EXCLUSIVE     =4;
	
	public final static int SQLITE_SYNC_NORMAL        =0x00002;
	public final static int SQLITE_SYNC_FULL          =0x00003;
	public final static int SQLITE_SYNC_DATAONLY      =0x00010;
	
	public final static int SQLITE_STATIC      =0;
	public final static int SQLITE_TRANSIENT   =-1;
	
	public final static int SQLITE_INTEGER  =1;
	public final static int SQLITE_FLOAT    =2;
	public final static int SQLITE_BLOB     =4;
	public final static int SQLITE_NULL     =5;
	public final static int SQLITE_TEXT     =3;
	
	public final static int SQLITE_UTF8           =1;    /* IMP: R-37514-35566 */
	public final static int SQLITE_UTF16LE        =2;    /* IMP: R-03371-37637 */
	public final static int SQLITE_UTF16BE        =3;    /* IMP: R-51971-34154 */
	public final static int SQLITE_UTF16          =4;    /* Use native byte order */
	public final static int SQLITE_ANY            =5;    /* Deprecated */
	public final static int SQLITE_UTF16_ALIGNED  =8;    /* sqlite3_create_collation only */

	public final static int SQLITE_MUTEX_FAST             =0;
	public final static int SQLITE_MUTEX_RECURSIVE        =1;
	public final static int SQLITE_MUTEX_STATIC_MASTER    =2;
	public final static int SQLITE_MUTEX_STATIC_APP1      =8;  /* For use by application */
	public final static int SQLITE_MUTEX_STATIC_APP2      =9;  /* For use by application */
	public final static int SQLITE_MUTEX_STATIC_APP3     =10;  /* For use by application */

}

