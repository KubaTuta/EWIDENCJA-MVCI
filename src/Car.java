public class Car {
    private static int counter = 0;
    public final int index;
    private final String regNumber;
    private final String vin;
    private final String status;
    private final String userCompanyName;
    private final String dateOfCollection;
    private final String brand;
    private final String model;
    private final String bodyType;
    private final String version;
    private final String fuelType;
    private final String colour;
    private final String mileage;
    private final String productionYear;
    private final String firstRegDate;
    private final String comment;
    private final String auctionPricePlusFee;
    private final String auctionFee;
    private final String salesPrice;
    private final String salesChannel;
    private final String buyersData;
    private final String vatID;
    private final String dateOfProforma;
    private final String dateOfPayment;
    private final String nac;
    private final String invoiceNumber;
    private final String dateOfInvoiceIssue;
    private final String insurer;
    private final String expirationDate;

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

    public String getRegNumber() {
        return regNumber;
    }

    public String getVin() {
        return vin;
    }
    public String getInvoiceNumber() {
        return invoiceNumber;
    }
    public String getDateOfInvoiceIssue() {
        return dateOfInvoiceIssue;
    }
    public String getComment() {
        return comment;
    }
}

