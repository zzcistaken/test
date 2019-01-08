package com.zzc.test.mybatis.zzz;

import java.util.Arrays;
import java.util.List;

/**
 * @author Clinton Begin
 */
public class SetSqlNode extends TrimSqlNode {

  private static List<String> suffixList = Arrays.asList(",");

  public SetSqlNode(Configuration configuration,SqlNode contents) {
    super(configuration, contents, "SET", null, null, suffixList);
  }

}
