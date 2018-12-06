package com.daiv.cloudresolution.jsonEntity;

public class DescribeDomainRecords {

    private int PageNumber;
    private int TotalCount;
    private int PageSize;
    private String RequestId;
    private DomainRecords DomainRecords;
    public void setPageNumber(int PageNumber) {
         this.PageNumber = PageNumber;
     }
     public int getPageNumber() {
         return PageNumber;
     }

    public void setTotalCount(int TotalCount) {
         this.TotalCount = TotalCount;
     }
     public int getTotalCount() {
         return TotalCount;
     }

    public void setPageSize(int PageSize) {
         this.PageSize = PageSize;
     }
     public int getPageSize() {
         return PageSize;
     }

    public void setRequestId(String RequestId) {
         this.RequestId = RequestId;
     }
     public String getRequestId() {
         return RequestId;
     }

    public void setDomainRecords(DomainRecords DomainRecords) {
         this.DomainRecords = DomainRecords;
     }
     public DomainRecords getDomainRecords() {
         return DomainRecords;
     }

}