drop table mappingitem if exists;

create table mappingitem (ID bigint identity primary key, 
    mapname varchar(50) NOT NULL,
	hl7 varchar(50) NOT NULL,
	fhir varchar(50) NOT NULL,
);