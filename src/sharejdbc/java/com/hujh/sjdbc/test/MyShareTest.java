package com.hujh.sjdbc.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.dangdang.ddframe.rdb.sharding.api.rule.BindingTableRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;

public class MyShareTest {

  public static void main(String[] args) {

    Map<String, DataSource> dataSourceMap = new HashMap<>(2);
    dataSourceMap.put("ds_0", createDataSource("ds_0"));
    dataSourceMap.put("ds_1", createDataSource("ds_1"));

    DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap);

    
    TableRule orderTableRule = new TableRule("t_order", Arrays.asList("t_order_0", "t_order_1"), dataSourceRule);
    TableRule orderItemTableRule = new TableRule("t_order_item", Arrays.asList("t_order_item_0", "t_order_item_1"), dataSourceRule);
    
    ShardingRule shardingRule = new ShardingRule(dataSourceRule, Arrays.asList(orderTableRule, orderItemTableRule),
                  Arrays.asList(new BindingTableRule(Arrays.asList(orderTableRule, orderItemTableRule))),
                  new DatabaseShardingStrategy("user_id", new SingleKeyModuloDatabaseShardingAlgorithm()),
                  new TableShardingStrategy("order_id", new SingleKeyModuloTableShardingAlgorithm()));
    
    // new ShardingRule(dataSourceRule, tableRules, bindingTableRules, databaseShardingStrategy, tableShardingStrategy)
    
    
    
  }

  private static DataSource createDataSource(final String dataSourceName) {
    BasicDataSource result = new BasicDataSource();
    result.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
    result.setUrl(String.format("jdbc:mysql://localhost:3306/%s", dataSourceName));
    result.setUsername("admin");
    result.setPassword("admin");
    return result;
  }

  
}
