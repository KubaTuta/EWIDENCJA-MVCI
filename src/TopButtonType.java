public enum TopButtonType {
    COMBO("Combo", "Wklej tekst"),
    REG("Nr rej. / VIN", "Wpisz nr rej. lub VIN, aby wyświetlić parametry pojazdu"),
    COMMENTS("Komentarze", "Wpisz datę, aby wyświetlić pojazdy z komentarzami z tego dnia (dd.mm.rrrr)"),
    INVOICES("Faktury danego dnia", "Wpisz datę, aby wyświetlić listę faktur z tego dnia (dd.mm.rrrr)");

    private final String title;
    private final String label;

    TopButtonType(String title, String label) {
        this.title = title;
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public String getLabel() {
        return label;
    }
}