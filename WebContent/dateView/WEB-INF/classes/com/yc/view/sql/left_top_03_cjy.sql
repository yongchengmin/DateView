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
    select to_char(wpt.require_arrive_date,'yyyy-mm-dd') as require_arrive_date,
    ptd.production_line,
    sum(ptd.expected_quantity_bu) as expected_quantity_bu,
    sum(ptd.picked_quantity_bu) as picked_quantity_bu
    from 
    (select pick_ticket_id,
    case production_line when '帅铃南线' then 'XG01Z1' 
      when '帅南' then 'XG01Z1'
      when '帅铃北线' then 'XG01Z2'
      when '帅北' then 'XG01Z2'
      when '骏铃南线' then 'XG02Z1'
      when '骏南' then 'XG02Z1'  
      when '骏铃北线' then 'XG02Z2'
      when '骏北' then 'XG02Z2' else production_line end as production_line,
    expected_quantity_bu,picked_quantity_bu
    from wms_pick_ticket_detail 
    where lot_pick_code is null) ptd 
    
    left join wms_pick_ticket wpt on ptd.pick_ticket_id=wpt.id
    where to_char(wpt.require_arrive_date,'yyyy-mm-dd')>= need_time1
    and to_char(wpt.require_arrive_date,'yyyy-mm-dd')<= need_time3
    group by to_char(wpt.require_arrive_date,'yyyy-mm-dd'),ptd.production_line;
  
  loop
    fetch cur_01 into rec_01;
    exit when cur_01%notfound;
    
    if (rec_01.v2='XG01Z1') then
      if rec_01.v1= need_time1 then
      s1 := round((rec_01.v4/rec_01.v3)*100,2);
      elsif rec_01.v1=need_time2 then
      s2 := round((rec_01.v4/rec_01.v3)*100,2);
      elsif rec_01.v1=need_time3 then
      s3 := round((rec_01.v4/rec_01.v3)*100,2);
      end if;
      
    elsif (rec_01.v2='XG01Z2') then
      if rec_01.v1=need_time1 then
      s4 := round((rec_01.v4/rec_01.v3)*100,2);
    dbms_output.put_line('need_time1:'||s4);
      elsif rec_01.v1=need_time2 then
      s5 := round((rec_01.v4/rec_01.v3)*100,2);
    dbms_output.put_line('need_time2:'||s5);
      elsif rec_01.v1=need_time3 then
      s6 := round((rec_01.v4/rec_01.v3)*100,2);
    dbms_output.put_line('need_time3:'||s6);
      end if;
      
    elsif (rec_01.v2='XG02Z1') then
      if rec_01.v1=need_time1 then
      s7 :=round((rec_01.v4/rec_01.v3)*100,2);
      elsif rec_01.v1=need_time2 then
      s8 := round((rec_01.v4/rec_01.v3)*100,2);
      elsif rec_01.v1=need_time3 then
      s9 := round((rec_01.v4/rec_01.v3)*100,2);
      end if;
      
    elsif (rec_01.v2='XG02Z2') then
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
      
      INSERT INTO LEFT_TOP_HEAD_TEMP (TITLE,CATEGORYAXISLABEL,VALUEAXISLABEL,TYPE) VALUES ('三日产线备料进度','日期','完成进度(%)','left_top');
      COMMIT;
    
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE,TYPE) VALUES ('帅岭南线',need_time1,s1,1,'left_top');
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE,TYPE) VALUES ('帅岭南线',need_time2,s2,1,'left_top');
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE,TYPE) VALUES ('帅岭南线',need_time3,s3,1,'left_top');
    
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE,TYPE) VALUES ('帅岭北线',need_time1,s4,2,'left_top');
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE,TYPE) VALUES ('帅岭北线',need_time2,s5,2,'left_top');
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE,TYPE) VALUES ('帅岭北线',need_time3,s6,2,'left_top');
    
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE,TYPE) VALUES ('骏铃南线',need_time1,s7,3,'left_top');
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE,TYPE) VALUES ('骏铃南线',need_time2,s8,3,'left_top');
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE,TYPE) VALUES ('骏铃南线',need_time3,s9,3,'left_top'); 
    
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE,TYPE) VALUES ('骏铃北线',need_time1,s10,4,'left_top');
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE,TYPE) VALUES ('骏铃北线',need_time2,s11,4,'left_top');
      INSERT INTO LEFT_TOP_Y_TEMP(CATEGORIES,X,QUANTITY,LINE,TYPE) VALUES ('骏铃北线',need_time3,s12,4,'left_top');
      
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