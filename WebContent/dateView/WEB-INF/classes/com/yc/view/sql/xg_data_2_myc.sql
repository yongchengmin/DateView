declare
  times     varchar2(20) := to_char(sysdate,'yyyy-MM-dd HH24:mi:ss');
  trunc_date  DATE := to_date(times,'yyyy-MM-dd hh24:mi:ss');
  m10  FLOAT := 0;--已完成总数
  m11  FLOAT := 0;
  m12  FLOAT := 0;
  m13  FLOAT := 0;
  m14  FLOAT := 0;
  m15  FLOAT := 0;--未完成总数
  type re_mo_01 is record(
    m1   FLOAT,            
    m2   FLOAT
  );
  re_m_1 re_mo_01;
  type balance is ref cursor;
  cur1 balance;
 begin
  select count(t.id)  into m15
  from thorn_tasks t
  where t.type = 'CREATE_BOL' and (t.status > 'FINISH' or t.status < 'FINISH')
  and to_char(t.create_time,'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd')
  ;
  case when m15 > 0 then
    --dbms_output.put_line('有未完成的,则显示未完成监控');
    open cur1 for 
    select distinct t.id as thorn_tasks_id,
    trunc(trunc_date - to_date(to_char(t.create_time,'yyyy-MM-dd hh24:mi:ss'),'yyyy-MM-dd hh24:mi:ss'),4)*24*60 as dif
    from thorn_tasks t 
    left join w_bol_s wb on wb.id=t.message_id
    left join w_container_s ws on ws.w_bol_s_id=wb.id
    left join wms_task  task on task.box_tag=ws.container
    where t.type = 'CREATE_BOL' and task.status = 'OPEN' and task.box_tag is not null
    and to_char(t.create_time,'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd')
    ;
    loop
      fetch cur1 into re_m_1;
      exit when cur1%notfound;
      m11 := m11+1;
      
      if re_m_1.m2 <= 3 then
        m12 := m12+1;
      elsif re_m_1.m2 > 3 and re_m_1.m2 <= 5 then
        m13 := m13+1;
      else
        m14 := m14+1;
      end if;
    end loop;   
    close cur1;
  else
      --dbms_output.put_line('都完成了,则显示已完成监控');
      select count(t.id)  into m10
      from thorn_tasks t
      where t.type = 'CREATE_BOL' and t.status = 'FINISH'
      and to_char(t.create_time,'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd');
  end case;
  
  begin
    execute immediate 'truncate table LEFT_TOP_HEAD_TEMP';
    execute immediate 'truncate table LEFT_TOP_Y_TEMP';
    
    if m15 > 0 then
      INSERT INTO LEFT_TOP_HEAD_TEMP (TITLE,CATEGORYAXISLABEL,VALUEAXISLABEL) VALUES ('装车单未执行监控','','未执行(个)');
      COMMIT;
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE) VALUES ('任务数',times,m11,2);
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE) VALUES ('3分钟以内',times,m12,3);
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE) VALUES ('3-5分钟',times,m13,4);
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE) VALUES ('5分钟以上',times,m14,5);
      COMMIT;
    else
      INSERT INTO LEFT_TOP_HEAD_TEMP (TITLE,CATEGORYAXISLABEL,VALUEAXISLABEL) VALUES ('装车单已完成监控','','执行(个)');
      COMMIT;
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE) VALUES ('完成任务数',times,m10,1);
      COMMIT;
    end if;      
  end;
  
  begin
  execute immediate 'truncate table LEFT_TOP_HEAD';
  execute immediate 'alter table LEFT_TOP_HEAD nologging';
  insert /*+append*/ into LEFT_TOP_HEAD select * from LEFT_TOP_HEAD_TEMP;
  commit;
  
  execute immediate 'truncate table LEFT_TOP_Y';
  execute immediate 'alter table LEFT_TOP_Y nologging';
  insert /*+append*/ into LEFT_TOP_Y select * from LEFT_TOP_Y_TEMP;
  commit;
  end;
 end;