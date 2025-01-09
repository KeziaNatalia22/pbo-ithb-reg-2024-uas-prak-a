package Model;

import java.util.Date;

public class ShipmentDetails {
    private int id, transactionId;
    private StatusEnum status;
    private String currentPosition, updateBy, evidence;
    private String date;
    public ShipmentDetails(int id, int transactionId, StatusEnum status, String currentPosition, String updateBy,
            String evidence, String date) {
        this.id = id;
        this.transactionId = transactionId;
        this.status = status;
        this.currentPosition = currentPosition;
        this.updateBy = updateBy;
        this.evidence = evidence;
        this.date = date;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
    public StatusEnum getStatus() {
        return status;
    }
    public void setStatus(StatusEnum status) {
        this.status = status;
    }
    public String getCurrentPosition() {
        return currentPosition;
    }
    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }
    public String getUpdateBy() {
        return updateBy;
    }
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
    public String getEvidence() {
        return evidence;
    }
    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
