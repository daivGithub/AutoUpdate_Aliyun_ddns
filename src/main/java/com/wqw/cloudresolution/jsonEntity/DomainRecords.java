package com.wqw.cloudresolution.jsonEntity;
import java.util.List;

public class DomainRecords {

    private List<Record> Record;
    public void setRecord(List<Record> Record) {
         this.Record = Record;
     }
     public List<Record> getRecord() {
         return Record;
     }

}