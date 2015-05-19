
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


public class Environment implements Copyable, Serializable
{

    public float windSpeed = 0;
    public float windDirection = 0;


    public Environment() {

    }


    public Environment(Environment other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        Environment self;
        self = new Environment();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        windSpeed = 0;
            
        windDirection = 0;
            
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        Environment otherObj = (Environment)o;



        if(windSpeed != otherObj.windSpeed) {
            return false;
        }
            
        if(windDirection != otherObj.windDirection) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += (int)windSpeed;
                
        __result += (int)windDirection;
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>EnvironmentTypeSupport</code>
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
        

        Environment typedSrc = (Environment) src;
        Environment typedDst = this;

        typedDst.windSpeed = typedSrc.windSpeed;
            
        typedDst.windDirection = typedSrc.windDirection;
            
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
        strBuffer.append("windSpeed: ").append(windSpeed).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("windDirection: ").append(windDirection).append("\n");
            
        return strBuffer.toString();
    }
    
}

