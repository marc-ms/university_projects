package edu.uoc.pac2;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebDomain {
    // atributos de la clase
    private String domain;
    private String ownerName;
    private String ownerEmail;
    private String hostingProvider;
    private LocalDate registrationDate;
    private LocalDate expirationDate;
    private int maxConcurrentUsers;
    private boolean whoisPrivacy;
    private boolean autoRenew;
    private static String validExtensions[] = {"com", "org", "net", "int", "edu", "gov", "mil"};
    private static double renewalCost = 9.99;


    public WebDomain(String domain, String ownerName, String ownerEmail, String hostingProvider, LocalDate registrationDate, LocalDate expirationDate, int maxConcurrentUsers, boolean whoisPrivacy, boolean autoRenew){
        setDomain(domain);
        setOwnerName(ownerName);
        setOwnerEmail(ownerEmail);
        setHostingProvider(hostingProvider);
        setRegistrationDate(registrationDate);
        setExpirationDate(expirationDate);
        setMaxConcurrentUsers(maxConcurrentUsers);
        setWhoisPrivacy(whoisPrivacy);
        setAutoRenew(autoRenew);
    }

    // definimos los setters y getters
    public String getDomain(){
        return domain;
    }

    // metodo que comprueba la validez del dominio
    private boolean isValidDomain(String domain){
        // definimos el regex necesario para hacer la comprobacion de dominio
        Pattern pattern = Pattern.compile("^([a-z0-9]+(-[a-z0-9]+)*\\.)(com|org|net|int|edu|gov|mil)$");
        Matcher matcher = pattern.matcher(domain);
        boolean matchFound = matcher.find();
        return matchFound;
    }

    public void setDomain(String domain){
        // pasamos el método isValidDomain al setter
        if (domain != null) {
            String lowerCaseDomain = domain.toLowerCase();
            if (isValidDomain(lowerCaseDomain)) {
                this.domain = lowerCaseDomain; // a this.domain (que pertenece a la clase WebDoamin) le asignamos el valor del parametro del setter
            } else{
                System.out.print("[ERROR] Invalid domain format.");
            }
        } else {
            System.out.print("[ERROR] Invalid domain format.");
        }
    }

    // ownerName

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {

        if (ownerName == null || ownerName.trim().isEmpty()){
            System.out.print("[ERROR] Owner name cannot be empty.");
        } else if (ownerName.matches("^[\s].+[\s]$")) {
            String result = ownerName.replaceAll("\\s+", " ");
            this.ownerName = result.trim();
        } else {
            this.ownerName = ownerName;
        }
    }


    // ownwerEmail
    public String getOwnerEmail() {
        return ownerEmail;
    }

    private boolean isValidEmail(String ownerEmail){
        // definimos el regex necesario para hacer la comprobacion de dominio
        Pattern pattern = Pattern.compile("(^[a-zA-Z0-9._-]+)+@([a-z0-9-]+\\.)(com|org|net|int|edu|gov|mil)");
        Matcher matcher = pattern.matcher(ownerEmail);
        boolean matchFound = matcher.find();
        return matchFound;
    }

    public void setOwnerEmail(String ownerEmail) {
        if (ownerEmail != null && isValidEmail(ownerEmail)) {
            this.ownerEmail = ownerEmail;
        } else{
            System.out.print("[ERROR] Invalid owner email format.");
        }
    }

    // hostingProvider
    public String getHostingProvider() {
        return hostingProvider;
    }

    public void setHostingProvider(String hostingProvider) {
        if (hostingProvider == null || hostingProvider.trim().isEmpty()) {
            System.out.print("[ERROR] Hosting provider cannot be empty.");
        } else if (hostingProvider.matches("^[\s].+[\s]$")) {
            String result = hostingProvider.replaceAll("\\s+", " ");
            this.hostingProvider = result.trim();
        } else {
            this.hostingProvider = hostingProvider;
        }
    }

    // registrationDate
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        if (registrationDate == null || registrationDate.isAfter(LocalDate.now())) {
            System.out.print("[ERROR] The registration date must be prior or equal to the current date.");
        } else{
            this.registrationDate = registrationDate;
        }
    }

    // expirationDate
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        if (expirationDate == null || expirationDate.isBefore(getRegistrationDate())) {
            System.out.print("[ERROR] The expiration date must be later than the registration date.");
        } else{
            this.expirationDate = expirationDate;
        }
    }

    //maxConcurrtnUsers
    public int getMaxConcurrentUsers() {
        return maxConcurrentUsers;
    }

    public void setMaxConcurrentUsers(int maxConcurrentUsers) {
        if (maxConcurrentUsers > 0){
            this.maxConcurrentUsers = maxConcurrentUsers;
        } else {
            System.out.print("[ERROR] Maximum concurrent users must be greater than 0.");
        }

    }

    // whoisPrivacy
    public boolean isWhoisPrivacy() {
        return whoisPrivacy;
    }

    public void setWhoisPrivacy(boolean whoisPrivacy) {
        this.whoisPrivacy = whoisPrivacy;
    }

    // autorenew
    public boolean isAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    // isExpired
    public boolean isExpired() {
        return LocalDate.now().isAfter(getExpirationDate());
    }

    public boolean isAboutToExpire(){
        return ChronoUnit.MONTHS.between(LocalDate.now(), getExpirationDate()) < 1;
    }

    public String domainConcurrenceStatus(int concurrentUsers){
        // forzamos el punto flotante para que la division no sea entera
        float usersInterval = ((float) concurrentUsers/getMaxConcurrentUsers()) * 100;
        if (0 <= usersInterval && usersInterval < 20){
            return "Low";
        } else if (20 <= usersInterval && usersInterval < 80) {
            return "Average";
        } else if (80 <= usersInterval && usersInterval <= 100) {
            return "High";
        } else {
            System.out.print("[ERROR] The number of concurrent users exceeds the maximum allowed");
            return "";
        }
    }

    public double predictedRenewalCostByDate(LocalDate date){
        double cost = 0;
        int yearsRegisteredDomain;
        int yearsForExpiration;
        int[] discounts =  {5, 10, 15};

        if (LocalDate.now().isAfter(date)) {
            System.out.print("[ERROR] The date must be later than the current date.");
        } else if (!isAutoRenew()) {
            return 0;
        } else {
            yearsRegisteredDomain = Period.between(getRegistrationDate(), date).getYears();
            yearsForExpiration = Period.between(getExpirationDate(), date).getYears();
            int[] antiquityYearsArray = new int[yearsRegisteredDomain];
            LocalDate[] renovationYearsArray = new LocalDate[yearsForExpiration + 1];

            // inicializamos la antiguedad
            for (int i = 0; i < yearsRegisteredDomain; antiquityYearsArray[i] = ++i);
            // cada año de renovación
            for (int i = 0; i <= yearsForExpiration; i++){
                renovationYearsArray[i] = getExpirationDate().plusYears(i);
            }

            int i = 0;
            while (renovationYearsArray[i].isBefore(date)) {
                if (antiquityYearsArray[i] == 1 && yearsRegisteredDomain < 9) {
                    // no aplica descuento
                    cost += renewalCost;
                } else if ((antiquityYearsArray[i] == 2 || antiquityYearsArray[i] == 3) && yearsRegisteredDomain < 9) {
                    cost += renewalCost - (renewalCost * discounts[0]) / 100;
                } else if ((antiquityYearsArray[i] == 4 || antiquityYearsArray[i] == 5) && yearsRegisteredDomain < 9) {
                    cost += renewalCost - (renewalCost * discounts[1]) / 100;
                } else {
                    cost += renewalCost - ((renewalCost * discounts[2]) / 100);
                }
                if (i == renovationYearsArray.length - 1){
                    break;
                }
                i++;
            }
        }
        return cost;
    }

}
