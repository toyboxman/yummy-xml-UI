CREATE   TABLE   TranJrnl   
(
	type   integer,
	trantype    integer,
	sysid   integer,
	code char(16),
	destination char(4),
	"from"   char(4),
	starttime   integer,
	overtime    integer,
	endtime     integer,
	datatype    integer,
	id          integer,
	currentstep integer,
	routeid     integer,
	pack        char(16),
	unpack      char(16),
	rpack       char(16),
	runpack     char(16),
	dpack      char(16),
	dayoff     integer,
	revmode     integer,
	isend       integer,
	isreverse   integer,
	transtat    integer,
	reversetimes     integer,
                      newtran     integer  
);

CREATE INDEX IDX_ID_CURRENTSTEP
on TranJrnl (id ,currentstep);

CREATE   TABLE   HisTranJrnl   
(
	type  integer,
	trantype    integer,
	sysid   integer,
	code        char(16),
	destination char(4),
	"from"        char(4),
	starttime   integer,
	overtime    integer,
	endtime     integer,
	datatype    integer,
	id          integer,
	currentstep integer,
	routeid     integer,
	pack        char(16),
	unpack      char(16),
	rpack       char(16),
	runpack     char(16),
	dpack      char(16),
	dayoff     integer,
	revmode     integer,
	isend       integer,
	isreverse   integer,
	transtat    integer,
	reversetimes     integer,
                     newtran     integer  
);

CREATE INDEX IDX_HID_CURRENTSTEP
on HisTranJrnl (id ,currentstep);

CREATE   TABLE   RevFail   
(
	type   integer,
	trantype    integer,
	sysid   integer,
	code        char(16),
	destination char(4),
	"from"        char(4),
	starttime   integer,
	overtime    integer,
	endtime     integer,
	datatype    integer,
	id          integer,
	currentstep integer,
	routeid     integer,
	pack        char(16),
	unpack      char(16),
	rpack       char(16),
	runpack     char(16),
	dpack      char(16),
	dayoff     integer,
	revmode     integer,
	isend       integer,
	isreverse   integer,
	transtat    integer,
	reversetimes     integer,
                      newtran     integer  
);

CREATE INDEX IDX_RFID_CURRENTSTEP
on RevFail (id ,currentstep);

CREATE   TABLE   RevSucc   
(
	type   integer,
	trantype    integer,
	sysid   integer,
	code        char(16),
	destination char(4),
	"from"        char(4),
	starttime   integer,
	overtime    integer,
	endtime     integer,
	datatype    integer,
	id          integer,
	currentstep integer,
	routeid     integer,
	pack        char(16),
	unpack      char(16),
	rpack       char(16),
	runpack     char(16),
	dpack      char(16),
	dayoff     integer,
	revmode     integer,
	isend       integer,
	isreverse   integer,
	transtat    integer,
	reversetimes     integer,
                     newtran     integer  
);

CREATE INDEX IDX_RSID_CURRENTSTEP
on RevSucc (id ,currentstep);

CREATE   TABLE   AllHisTranJrnl   
(
	type   integer,
	trantype    integer,
	sysid   integer,
	code        char(16),
	destination char(4),
	"from"        char(4),
	starttime   integer,
	overtime    integer,
	endtime     integer,
	datatype    integer,
	id          integer,
	currentstep integer,
	routeid     integer,
	pack        char(16),
	unpack      char(16),
	rpack       char(16),
	runpack     char(16),
	dpack      char(16),
	dayoff     integer,
	revmode     integer,
	isend       integer,
	isreverse   integer,
	transtat    integer,
	reversetimes     integer,
                      newtran     integer  
);

CREATE INDEX IDX_HID_ALLCURRENTSTEP
on AllHisTranJrnl (id ,currentstep);

CREATE   TABLE   TranDataJrnl   
(
	trid   integer,
	currentstep   	integer,
	trantype   integer,
	code          	char(16),
	account     	char(32),
	name        	char(16),
	toaccout	char(32),
	toname        	char(16),
	money        	char(16),
	trandate        char(16),
	trantime	    char(16),  
	tranterminal	char(16),
	transtate   	integer
);

CREATE INDEX IDX_HID_TranDatacJrnl
on TranDataJrnl (trid, currentstep, trantype);


CREATE   TABLE   terminalinfo  
(
terminalno      varchar(16) NOT NULL PRIMARY KEY,    
terminalip      varchar(16),   
terminalstatus  integer,     
terminalid      varchar(16),     
terminaladdr    varchar(128), 
terminalcall    varchar(16)     
);

CREATE INDEX IDX_terminalinfo on terminalinfo (terminalno);


CREATE   TABLE   terminaldevice
(
	terminalno      varchar(16) PRIMARY KEY,   
	printerstate    integer,   
	inicstate       integer,    
	keyboardstate   integer,  
	magstate        integer
	
);

CREATE INDEX IDX_HID_terminaldevice
on terminaldevice (terminalno);


CREATE   TABLE   terminalkey
(
	makey      varchar(17),
	masterkey      varchar(17),
	worksecretkey      varchar(17)
);

CREATE   TABLE   terminalmgr
(
	name         varchar(16) NOT NULL PRIMARY KEY,
	credentials   varchar(16)
);



