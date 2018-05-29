declare
  need_time1 varchar(32) := to_char(sysdate,'yyyy-mm-dd');
  need_time2 varchar(32) := to_char(sysdate+1,'yyyy-mm-dd');
  need_time3 varchar(32) := to_char(sysdate+2,'yyyy-mm-dd');

  type cur is ref cursor;
  cur_01 cur;

  type rec is record(
  v1 varchar2(32),
  v2 varchar2(32),
  v3 float,
  v4 float
  );
  rec_01 rec;

  s1 float;
  s2 float;
  s3 float;
  s4 float;
  s5 float;
  s6 float;
  s7 float;
  s8 float;
  s9 float;
  s10 float;
  s11 float;
  s12 float;

begin
  open cur_01 for 
    select to_char(wpt.require_arrive_date,'yyyy-mm-dd'),
    ptd.production_line,
    sum(ptd.expected_quantity_bu),
    sum(ptd.picked_quantity_bu)
    from wms_pick_ticket_detail ptd 
    left join wms_pick_ticket wpt on ptd.pick_ticket_id=wpt.id
    where to_char(wpt.require_arrive_date,'yyyy-mm-dd')>= need_time1
    and to_char(wpt.require_arrive_date,'yyyy-mm-dd')<= need_time3
    and ptd.lot_pick_code is null
    group by to_char(wpt.require_arrive_date,'yyyy-mm-dd'),ptd.production_line;
	
  loop
    fetch cur_01 into rec_01;
    exit when cur_01%notfound;
    
    if (rec_01.v2='XG01Z1' or rec_01.v2='帅铃南线' or rec_01.v2='帅南') then
      if rec_01.v1= need_time1 then
      s1 := round((rec_01.v4/rec_01.v3)*100,2);
      elsif rec_01.v1=need_time2 then
      s2 := round((rec_01.v4/rec_01.v3)*100,2);
      elsif rec_01.v1=need_time3 then
      s3 := round((rec_01.v4/rec_01.v3)*100,2);
      end if;
      
    elsif (rec_01.v2='XG01Z2' or rec_01.v2='帅铃北线' or rec_01.v2='帅北') then
      if rec_01.v1=need_time1 then
      s4 := round((rec_01.v4/rec_01.v3)*100,2);
      elsif rec_01.v1=need_time2 then
      s5 := round((rec_01.v4/rec_01.v3)*100,2);
      elsif rec_01.v1=need_time3 then
      s6 := round((rec_01.v4/rec_01.v3)*100,2);
      end if;
      
    elsif (rec_01.v2='XG02Z1' or rec_01.v2='骏铃南线' or rec_01.v2='骏南') then
      if rec_01.v1=need_time1 then
      s7 :=round((rec_01.v4/rec_01.v3)*100,2);
      elsif rec_01.v1=need_time2 then
      s8 := round((rec_01.v4/rec_01.v3)*100,2);
      elsif rec_01.v1=need_time3 then
      s9 := round((rec_01.v4/rec_01.v3)*100,2);
      end if;
      
    elsif (rec_01.v2='XG02Z2' or rec_01.v2='骏铃北线' or rec_01.v2='骏北') then
      if rec_01.v1=need_time1 then
      s10 := round((rec_01.v4/rec_01.v3)*100,2);
      elsif rec_01.v1=need_time2 then
      s11 := round((rec_01.v4/rec_01.v3)*100,2);
      elsif rec_01.v1=need_time3 then
      s12 := round((rec_01.v4/rec_01.v3)*100,2);
      end if;
      
    end if;
    
  end loop;
  close cur_01;

    begin 
      execute immediate 'truncate table LEFT_TOP_HEAD_TEMP';
      execute immediate 'truncate table LEFT_TOP_Y_TEMP';
      
      INSERT INTO LEFT_TOP_HEAD_TEMP (TITLE,CATEGORYAXISLABEL,VALUEAXISLABEL) VALUES ('三日产线备料进度','日期','完成进度(%)');
      COMMIT;
    
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE) VALUES ('帅岭南线',need_time1,s1,1);
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE) VALUES ('帅岭南线',need_time2,s2,1);
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE) VALUES ('帅岭南线',need_time3,s3,1);
    
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE) VALUES ('帅岭北线',need_time1,s4,2);
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE) VALUES ('帅岭北线',need_time2,s5,2);
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE) VALUES ('帅岭北线',need_time3,s6,2);
    
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE) VALUES ('骏铃南线',need_time1,s7,3);
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE) VALUES ('骏铃南线',need_time2,s8,3);
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE) VALUES ('骏铃南线',need_time3,s9,3); 
    
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE) VALUES ('骏铃北线',need_time1,s10,4);
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE) VALUES ('骏铃北线',need_time2,s11,4);
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE) VALUES ('骏铃北线',need_time3,s12,4);
      
      COMMIT;

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
  
    