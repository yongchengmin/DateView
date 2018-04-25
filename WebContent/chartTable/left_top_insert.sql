declare
   times     varchar2(20) := to_char(sysdate,'yyyy-MM-dd HH24:mi:ss');

 begin
    execute immediate 'truncate table LEFT_TOP_HEAD';
    execute immediate 'truncate table LEFT_TOP_Y';
    
    INSERT INTO LEFT_TOP_HEAD (TITLE,CATEGORYAXISLABEL,VALUEAXISLABEL) VALUES ('装车单未执行监控','','未执行(个)');
    COMMIT;
    INSERT INTO LEFT_TOP_Y(CATEGORIES,X,QUANTITY,LINE) VALUES ('任务数',times,95,1);
    INSERT INTO LEFT_TOP_Y(CATEGORIES,X,QUANTITY,LINE) VALUES ('3分钟以内',times,78,1);
    INSERT INTO LEFT_TOP_Y(CATEGORIES,X,QUANTITY,LINE) VALUES ('3-5分钟',times,19,3);
    INSERT INTO LEFT_TOP_Y(CATEGORIES,X,QUANTITY,LINE) VALUES ('5分钟以上',times,6,4);
    COMMIT;      
 end;