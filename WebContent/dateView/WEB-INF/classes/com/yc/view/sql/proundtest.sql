declare

begin

    execute immediate 'truncate table RIGHT_BOTTOM_HEAD_TEMP';
    execute immediate 'truncate table RIGHT_BOTTOM_Y_TEMP';
    
    INSERT INTO RIGHT_BOTTOM_HEAD_TEMP (TITLE) VALUES ('标题1');
    COMMIT;
    
    INSERT INTO RIGHT_BOTTOM_Y_TEMP(CATEGORIES,QUANTITY,LINE) VALUES ('data1',8,1);
	INSERT INTO RIGHT_BOTTOM_Y_TEMP(CATEGORIES,QUANTITY,LINE) VALUES ('data2',3,2);
	INSERT INTO RIGHT_BOTTOM_Y_TEMP(CATEGORIES,QUANTITY,LINE) VALUES ('data3',1,3);
	INSERT INTO RIGHT_BOTTOM_Y_TEMP(CATEGORIES,QUANTITY,LINE) VALUES ('data4',6,4);
	INSERT INTO RIGHT_BOTTOM_Y_TEMP(CATEGORIES,QUANTITY,LINE) VALUES ('data5',8,5);
	INSERT INTO RIGHT_BOTTOM_Y_TEMP(CATEGORIES,QUANTITY,LINE) VALUES ('data6',4,6);
	INSERT INTO RIGHT_BOTTOM_Y_TEMP(CATEGORIES,QUANTITY,LINE) VALUES ('data7',4,7);
	INSERT INTO RIGHT_BOTTOM_Y_TEMP(CATEGORIES,QUANTITY,LINE) VALUES ('data8',1,8);
	INSERT INTO RIGHT_BOTTOM_Y_TEMP(CATEGORIES,QUANTITY,LINE) VALUES ('data9',1,9);
    COMMIT;

    execute immediate 'truncate table RIGHT_BOTTOM_HEAD';
    execute immediate 'alter table RIGHT_BOTTOM_HEAD nologging';
    insert /*+append*/ into RIGHT_BOTTOM_HEAD select * from RIGHT_BOTTOM_HEAD_TEMP;
    commit;
    
    execute immediate 'truncate table RIGHT_BOTTOM_Y';
    execute immediate 'alter table RIGHT_BOTTOM_Y nologging';
    insert /*+append*/ into RIGHT_BOTTOM_Y select * from RIGHT_BOTTOM_Y_TEMP;
    commit;
  
end;