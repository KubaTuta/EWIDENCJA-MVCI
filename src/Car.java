public class Car {
    private static int counter = 0;
    public final int index;
    private final String regNumber;
    public final String vin;
    public final String status;
    public final String userCompanyName;
    public final String dateOfCollection;
    public final String brand;
    public final String model;
    public final String bodyType;
    public final String version;
    public final String fuelType;
    public final String colour;
    public final String mileage;
    public final String productionYear;
    public final String firstRegDate;
    public final String comment;
    public final String auctionPricePlusFee;
    public final String auctionFee;
    public final String salesPrice;
    public final String salesChannel;
    public final String buyersData;
    public final String vatID;
    public final String dateOfProforma;
    public final String dateOfPayment;
    public final String nac;
    public final String invoiceNumber;
    public final String dateOfInvoiceIssue;
    public final String insurer;
    public final String expirationDate;

    public Car(String[] carAttributes) {
        this.index = counter++;
        this.regNumber = carAttributes[0];
        this.vin = carAttributes[1];
        this.status = carAttributes[2];
        this.userCompanyName = carAttributes[3];
        this.dateOfCollection = carAttributes[4];
        this.brand = carAttributes[5];
        this.model = carAttributes[6];
        this.bodyType = carAttributes[7];
        this.version = carAttributes[8];
        this.fuelType = carAttributes[9];
        this.colour = carAttributes[10];
        this.mileage = carAttributes[11];
        this.productionYear = carAttributes[12];
        this.firstRegDate = carAttributes[13];
        this.comment = carAttributes[18];
        this.auctionPricePlusFee = carAttributes[24];
        this.auctionFee = carAttributes[25];
        this.salesPrice = carAttributes[26];
        this.salesChannel = carAttributes[30];
        this.buyersData = carAttributes[31];
        this.vatID = carAttributes[32];
        this.dateOfProforma = carAttributes[33];
        this.dateOfPayment = carAttributes[34];
        this.nac = carAttributes[35];
        this.invoiceNumber = carAttributes[36];
        this.dateOfInvoiceIssue = carAttributes[37];
        this.insurer = carAttributes[39];
        this.expirationDate = carAttributes[40];
    }

    public String allAttributeNames() {
        String[] allAttributes = {
                regNumber, vin, status, userCompanyName, dateOfCollection, brand, model, bodyType, version, fuelType, colour, mileage, productionYear,
                firstRegDate, comment, auctionPricePlusFee, auctionFee, salesPrice, salesChannel, buyersData, vatID, dateOfProforma, dateOfPayment, nac,
                invoiceNumber, dateOfInvoiceIssue, insurer, expirationDate
        };
        return String.join(" / ", allAttributes);
    }
}