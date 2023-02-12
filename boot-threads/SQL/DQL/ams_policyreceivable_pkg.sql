
CREATE OR REPLACE PACKAGE ams_policyreceivable_pkg IS
    PROCEDURE get_policyreceivable_pro(v_subcompany VARCHAR2(20)) ;
    PROCEDURE get_policyfinancial_pro(v_subcompany VARCHAR2(20))  ;
END ams_policyreceivable_pkg ;
/


CREATE OR REPLACE PACKAGE BODY ams_policyreceivable_pkg IS
    PROCEDURE get_policyreceivable_pro(v_subcompany VARCHAR2(20)) IS
    BEGIN
        DBMS_LOCK.SLEEP(3);

    END ;
    PROCEDURE get_policyfinancial_pro(v_subcompany VARCHAR2(20)) IS
    BEGIN

    END ;
END ams_policyreceivable_pkg ;
/