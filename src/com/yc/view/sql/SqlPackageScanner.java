package com.yc.view.sql;

import java.io.IOException;
import java.util.Map;

public interface SqlPackageScanner {
	 public Map<String,String> getFullyQualifiedSqlNameList() throws IOException;
}
