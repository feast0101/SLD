package com.kmbt.csa.dav.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="all_log", catalog = "dbo")
public class DBDataModel  implements java.io.Serializable {

	 private static final long serialVersionUID = -8646278967746868290L;
	 private String companyname;
     private String functionality;
     private String storagetype;
     private Date date;
     private int usagecount;
     private int recno;
     
 	public DBDataModel() {
    }

    public DBDataModel(String companyname, String functionality, String storagetype, Date date, int usagecount) {
    	
       this.companyname = companyname;
       this.functionality = functionality;
       this.storagetype =storagetype;
       this.date = date;
       this.usagecount = usagecount;    
    }
    @Column(name="storageType")
	public String getStoragetype() {
		return storagetype;
	}

	public void setStoragetype(String storagetype) {
		this.storagetype = storagetype;
	}
	@Id    
    @Column(name="recno")
	public int getRecno() {
			return recno;
		}

	public void setRecno(int recno) {
			this.recno = recno;
		}
    @Column(name="tenantID")
    public String getCompanyName() {
        return this.companyname;
    }
    
    public void setCompanyName(String companyname) {
        this.companyname = companyname;
    }


    @Column(name="serviceName", length=50)
    public String getFunctionality() {
        return this.functionality;
    }
    
    public void setFunctionality(String functionality) {
        this.functionality = functionality;
    }

    @Column(name="yyyymm", length=19)
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    
    @Column(name="serviceCount")
    public int getUsageCount() {
        return this.usagecount;
    }
    
    public void setUsageCount(int usagecount) {
        this.usagecount = usagecount;
    }
    
	@Override
	public String toString() {
		return "DBDataModel [companyname=" + companyname + ", functionality=" + functionality
				+ ", date=" + date +", usagecount=" + usagecount +"]";
	}
}


