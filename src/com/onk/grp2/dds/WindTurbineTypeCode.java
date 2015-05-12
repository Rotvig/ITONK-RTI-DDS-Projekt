package com.onk.grp2.dds;

/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
import com.rti.dds.typecode.*;


public class WindTurbineTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        StructMember sm[] = new StructMember[6];

        sm[i]=new StructMember("id",false,(short)-1,false,(TypeCode)TypeCode.TC_LONG,0,false); i++;
        sm[i]=new StructMember("orientation",false,(short)-1,false,(TypeCode)TypeCode.TC_FLOAT,1,false); i++;
        sm[i]=new StructMember("bladePitch",false,(short)-1,false,(TypeCode)TypeCode.TC_FLOAT,2,false); i++;
        sm[i]=new StructMember("RPM",false,(short)-1,false,(TypeCode)TypeCode.TC_FLOAT,3,false); i++;
        sm[i]=new StructMember("temperature",false,(short)-1,false,(TypeCode)TypeCode.TC_FLOAT,4,false); i++;
        sm[i]=new StructMember("production",false,(short)-1,false,(TypeCode)TypeCode.TC_FLOAT,5,false); i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_struct_tc("WindTurbine",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,sm);
        return tc;
    }
}
