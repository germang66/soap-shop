CREATE USER bills_ws IDENTIFIED BY A6B2ECTrCeesnygK;


GRANT CONNECT TO bills_ws;

GRANT CREATE SESSION TO bills_ws;


GRANT
  SELECT,
  INSERT,
  UPDATE
ON
  bill
TO
  bills_ws;

GRANT
  SELECT,
  INSERT,
  UPDATE
ON
  billitem
TO
  bills_ws;