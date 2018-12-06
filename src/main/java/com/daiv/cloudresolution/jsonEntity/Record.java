package com.daiv.cloudresolution.jsonEntity;

public class Record {

    private String RR;
    private String Status;
    private String Value;
    private int Weight;
    private String RecordId;
    private String Type;
    private String DomainName;
    private boolean Locked;
    private String Line;
    private int TTL;
    public void setRR(String RR) {
         this.RR = RR;
     }
     public String getRR() {
         return RR;
     }

    public void setStatus(String Status) {
         this.Status = Status;
     }
     public String getStatus() {
         return Status;
     }

    public void setValue(String Value) {
         this.Value = Value;
     }
     public String getValue() {
         return Value;
     }

    public void setWeight(int Weight) {
         this.Weight = Weight;
     }
     public int getWeight() {
         return Weight;
     }

    public void setRecordId(String RecordId) {
         this.RecordId = RecordId;
     }
     public String getRecordId() {
         return RecordId;
     }

    public void setType(String Type) {
         this.Type = Type;
     }
     public String getType() {
         return Type;
     }

    public void setDomainName(String DomainName) {
         this.DomainName = DomainName;
     }
     public String getDomainName() {
         return DomainName;
     }

    public void setLocked(boolean Locked) {
         this.Locked = Locked;
     }
     public boolean getLocked() {
         return Locked;
     }

    public void setLine(String Line) {
         this.Line = Line;
     }
     public String getLine() {
         return Line;
     }

    public void setTTL(int TTL) {
         this.TTL = TTL;
     }
     public int getTTL() {
         return TTL;
     }

    @Override
    public String toString() {
        return "Record{" +
                "RR='" + RR + '\'' +
                ", Status='" + Status + '\'' +
                ", Value='" + Value + '\'' +
                ", Weight=" + Weight +
                ", RecordId='" + RecordId + '\'' +
                ", Type='" + Type + '\'' +
                ", DomainName='" + DomainName + '\'' +
                ", Locked=" + Locked +
                ", Line='" + Line + '\'' +
                ", TTL=" + TTL +
                '}';
    }
}