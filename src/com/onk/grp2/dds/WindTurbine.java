package com.onk.grp2.dds;

/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    

import com.rti.dds.infrastructure.*;
import com.rti.dds.infrastructure.Copyable;

import java.io.Serializable;
import com.rti.dds.cdr.CdrHelper;


public class WindTurbine implements Copyable, Serializable
{

    public int id = 0;
    public float orientation = 0;
    public float bladePitch = 0;
    public float RPM = 0;
    public float temperature = 0;
    public float production = 0;


    public WindTurbine() {

    }


    public WindTurbine(WindTurbine other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        WindTurbine self;
        self = new WindTurbine();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        id = 0;
            
        orientation = 0;
            
        bladePitch = 0;
            
        RPM = 0;
            
        temperature = 0;
            
        production = 0;
            
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        WindTurbine otherObj = (WindTurbine)o;



        if(id != otherObj.id) {
            return false;
        }
            
        if(orientation != otherObj.orientation) {
            return false;
        }
            
        if(bladePitch != otherObj.bladePitch) {
            return false;
        }
            
        if(RPM != otherObj.RPM) {
            return false;
        }
            
        if(temperature != otherObj.temperature) {
            return false;
        }
            
        if(production != otherObj.production) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += (int)id;
                
        __result += (int)orientation;
                
        __result += (int)bladePitch;
                
        __result += (int)RPM;
                
        __result += (int)temperature;
                
        __result += (int)production;
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>WindTurbineTypeSupport</code>
     * rather than here by using the <code>-noCopyable</code> option
     * to rtiddsgen.
     * 
     * @param src The Object which contains the data to be copied.
     * @return Returns <code>this</code>.
     * @exception NullPointerException If <code>src</code> is null.
     * @exception ClassCastException If <code>src</code> is not the 
     * same type as <code>this</code>.
     * @see com.rti.dds.infrastructure.Copyable#copy_from(java.lang.Object)
     */
    public Object copy_from(Object src) {
        

        WindTurbine typedSrc = (WindTurbine) src;
        WindTurbine typedDst = this;

        typedDst.id = typedSrc.id;
            
        typedDst.orientation = typedSrc.orientation;
            
        typedDst.bladePitch = typedSrc.bladePitch;
            
        typedDst.RPM = typedSrc.RPM;
            
        typedDst.temperature = typedSrc.temperature;
            
        typedDst.production = typedSrc.production;
            
        return this;
    }


    
    public String toString(){
        return toString("", 0);
    }
        
    
    public String toString(String desc, int indent) {
        StringBuffer strBuffer = new StringBuffer();        
                        
        
        if (desc != null) {
            CdrHelper.printIndent(strBuffer, indent);
            strBuffer.append(desc).append(":\n");
        }
        
        
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("id: ").append(id).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("orientation: ").append(orientation).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("bladePitch: ").append(bladePitch).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("RPM: ").append(RPM).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("temperature: ").append(temperature).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("production: ").append(production).append("\n");
            
        return strBuffer.toString();
    }
    
}

