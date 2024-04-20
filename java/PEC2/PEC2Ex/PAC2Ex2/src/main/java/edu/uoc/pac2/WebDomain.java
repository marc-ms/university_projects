package edu.uoc.pac2;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents
 * @author marc
 * @version 1.0
 */
public class WebDomain {
    // atributos de la clase
    /**
     * WebDomain's domain, e.g. uoc.edu
     */
    private String domain;

    /**
     * WebDomain's ownerName, e.g. Gabriel Ferraté i Pascual
     */
    private String ownerName;

    /**
     * WebDomain's ownerEmail, e.g. gferrate@uoc.edu
     */
    private String ownerEmail;

    /**
     * WebDomain's hostingProvider, e.g. Hosting UOC
     */
    private String hostingProvider;

    /**
     * WebDomain's registrationDate, e.g. 10/04/1994
     */
    private LocalDate registrationDate;

    /**
     * WebDomain's expirationDate, e.g. 10/04/2027
     */
    private LocalDate expirationDate;

    /**
     * WebDomain's maxConcurrentUsers, e.g. 1000
     */
    private int maxConcurrentUsers;

    /**
     * WebDomain's whoisPrivacy, e.g. true
     */
    private boolean whoisPrivacy;

    /**
     * WebDomain's autoRenew, e.g. true
     */
    private boolean autoRenew;

    /**
     * WebDomain's validExtensions
     */
    private static String validExtensions[] = {"com", "org", "net", "int", "edu", "gov", "mil"};

    /**
     * WebDomain's renewalCost
     */
    private static double renewalCost = 9.99;

    /**
     * WebDomain's Constructor.
     * @param domain WebDomain's domain
     * @param ownerName WebDomain's owner's name
     * @param ownerEmail WebDomain's owner's email
     * @param hostingProvider WebDomain's hosting provider's name
     * @param registrationDate WebDomain's registration date of the domain
     * @param expirationDate WebDomain's expiration date of the domain
     * @param maxConcurrentUsers WebDomain's maximum number of concurrent users
     * @param whoisPrivacy WebDomain's privacy
     * @param autoRenew WebDomain's atutomatic renewal
     */
    public WebDomain(String domain, String ownerName, String ownerEmail, String hostingProvider, LocalDate registrationDate, LocalDate expirationDate, int maxConcurrentUsers, boolean whoisPrivacy, boolean autoRenew){
        // podemos inicializar los atributos de la clase
        // es buena practica utilizar los metodos setter y getter aqui OJO NO DEFINIRLOS, se definen fuera del constructor

        try{
            setDomain(domain);
            setOwnerName(ownerName);
            setOwnerEmail(ownerEmail);
            setHostingProvider(hostingProvider);
            setRegistrationDate(registrationDate);
            setExpirationDate(expirationDate);
            setMaxConcurrentUsers(maxConcurrentUsers);
            setWhoisPrivacy(whoisPrivacy);
            setAutoRenew(autoRenew);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * domain's getter.
     * @return the domain validated.
     */
    public String getDomain(){
        return domain;
    }

    /**
     * checks if domain is valid or not.
     * @param domain WebDomain's domain
     * @return true if valid domain or false if not valid.
     */
    private boolean isValidDomain(String domain){
        // definimos el regex necesario para hacer la comprobacion de dominio
        Pattern pattern = Pattern.compile("^([a-z0-9]+(-[a-z0-9]+)*\\.)(com|org|net|int|edu|gov|mil)$");
        Matcher matcher = pattern.matcher(domain);
        boolean matchFound = matcher.find();
        return matchFound;
    }

    /**
     * domain's setter with domain validation.
     * @param domain WebDomain's domain
     * @throws Exception When param "domain" is null or is not a valid domain.
     */
    public void setDomain(String domain) throws Exception{
        // pasamos el método isValidDomain al setter
        if (domain != null) {
            String lowerCaseDomain = domain.toLowerCase();
            if (isValidDomain(lowerCaseDomain)) {
                this.domain = lowerCaseDomain; // a this.domain (que pertenece a la clase WebDoamin) le asignamos el valor del parametro del setter
            } else{
                throw new Exception("[ERROR] Invalid domain format.");
            }
        } else {
            throw new Exception("[ERROR] Invalid domain format.");
        }
    }


    /**
     * owner's name getter.
     * @return the name of the domain's owner.
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * ownerName's setter with ownerName's validation.
     * @param ownerName WebDomain's owner's name
     * @throws Exception When param "ownerName" is null or empty string.
     */
    public void setOwnerName(String ownerName) throws Exception {

        if (ownerName == null || ownerName.trim().isEmpty()){
            throw new Exception("[ERROR] Owner name cannot be empty.");
        } else if (ownerName.matches("^[\s].+[\s]$")) {
            String result = ownerName.replaceAll("\\s+", " ");
            this.ownerName = result.trim();
        } else {
            this.ownerName = ownerName;
        }
    }

    /**
     * ownerEmail getter.
     * @return The email of the domain's owner.
     */
    public String getOwnerEmail() {
        return ownerEmail;
    }

    /**
     * Like method "isValidDomain" checks if email is valid or not.
     * @param ownerEmail WebDomain's owner's email
     * @return true if valid email or false if not.
     */
    private boolean isValidEmail(String ownerEmail){
        // definimos el regex necesario para hacer la comprobacion de dominio
        Pattern pattern = Pattern.compile("(^[a-zA-Z0-9._-]+)+@([a-z0-9-]+\\.)(com|org|net|int|edu|gov|mil)");
        Matcher matcher = pattern.matcher(ownerEmail);
        boolean matchFound = matcher.find();
        return matchFound;
    }

    /**
     * ownerEmail's setter with ownerEmail's validation.
     * @param ownerEmail WebDomain's owner's email
     * @throws Exception When ownerEmail is null or with not valid format.
     */
    public void setOwnerEmail(String ownerEmail) throws Exception{
        if (ownerEmail != null && isValidEmail(ownerEmail)) {
            this.ownerEmail = ownerEmail;
        } else{
            throw new Exception("[ERROR] Invalid owner email format.");
        }
    }

    /**
     * hostingProvider's getter.
     * @return The value of the hosting company where the domain is located.
     */
    public String getHostingProvider() {
        return hostingProvider;
    }

    /**
     * hostingProvider's setter that removes white spaces at the beginning and at the end of the param "hostingProvider".
     * @param hostingProvider WebDomain's hosting provider's name
     * @throws Exception When param "hostingProvider" is null or empty.
     */
    public void setHostingProvider(String hostingProvider) throws Exception{
        if (hostingProvider == null || hostingProvider.trim().isEmpty()) {
            throw new Exception("[ERROR] Hosting provider cannot be empty.");
        } else if (hostingProvider.matches("^[\s].+[\s]$")) {
            String result = hostingProvider.replaceAll("\\s+", " ");
            this.hostingProvider = result.trim();
        } else {
            this.hostingProvider = hostingProvider;
        }
    }

    /**
     * registrationDate's getter.
     * @return The value of the date on which the domain was first registered.
     */
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    /**
     * registrationDate's setter.
     * @param registrationDate WebDomain's registration date of the domain
     * @throws Exception When param "registrationDate" is null or is after current date.
     */
    public void setRegistrationDate(LocalDate registrationDate) throws Exception{
        if (registrationDate == null || registrationDate.isAfter(LocalDate.now())) {
            throw new Exception("[ERROR] The registration date must be prior or equal to the current date.");
        } else{
            this.registrationDate = registrationDate;
        }
    }

    /**
     * expirationDate's getter.
     * @return The value of the expiration date of the domain.
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    /**
     * expirationdate's setter.
     * @param expirationDate WebDomain's expiration date of the domain
     * @throws Exception When param "expirationDate" is null or is before getRegistrationDate's value.
     */
    public void setExpirationDate(LocalDate expirationDate) throws Exception{
        if (expirationDate == null || expirationDate.isBefore(getRegistrationDate())) {
            throw new Exception("[ERROR] The expiration date must be later than the registration date.");
        } else{
            this.expirationDate = expirationDate;
        }
    }

    /**
     * maxConcurrentUsers' getter.
     * @return The value of the maximum number of concurrent users that the domain can have according to its host configuration.
     */
    public int getMaxConcurrentUsers() {
        return maxConcurrentUsers;
    }

    /**
     * maxConcurrentUsers' setter.
     * @param maxConcurrentUsers WebDomain's maximum number of concurrent users
     * @throws Exception When param "maxConcurrentUsers" is equal or less than zero.
     */
    public void setMaxConcurrentUsers(int maxConcurrentUsers) throws Exception{
        if (maxConcurrentUsers > 0){
            this.maxConcurrentUsers = maxConcurrentUsers;
        } else {
            throw new Exception("[ERROR] Maximum concurrent users must be greater than 0.");
        }

    }

    /**
     * whoisPrivacy's boolean getter.
     * @return true or false.
     */
    public boolean isWhoisPrivacy() {
        return whoisPrivacy;
    }

    /**
     * whoisPrivacy's setter that determines whether the domain is private.
     * @param whoisPrivacy WebDomain's privacy
     */
    public void setWhoisPrivacy(boolean whoisPrivacy) {
        this.whoisPrivacy = whoisPrivacy;
    }

    /**
     * autoRenew's boolean getter.
     * @return true or false.
     */
    public boolean isAutoRenew() {
        return autoRenew;
    }

    /**
     * autoRenew's setter that determines whether the domain has automatic renewal activated.
     * @param autoRenew WebDomain's atutomatic renewal
     */
    public void setAutoRenew(boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    /**
     * isExpired's method that checks if a domain is expired.
     * @return true if current date is after getExpirationDate's value.
     */
    public boolean isExpired() {
        return LocalDate.now().isAfter(getExpirationDate());
    }

    /**
     * isAbouttoExpire's method that checks if a domain is about to expire.
     * @return true when there is less than a month left before the domain expires.
     */
    public boolean isAboutToExpire(){
        return ChronoUnit.MONTHS.between(LocalDate.now(), getExpirationDate()) < 1;
    }

    /**
     * Method that discretize the number of concurrent users depending on the maximum number of concurrent users that the domain can have in three ranges: Low, Average and High.
     * @param concurrentUsers number of currently active users
     * @return three possible string "Low", "Average" or "High" depending on param "concurrentUsers" and getMaxConcurrentUsers' value.
     * @throws Exception When param "concurrentUsers" is less than zero or greater than getMaxConcurrentUsers' value.
     */
    public String domainConcurrenceStatus(int concurrentUsers) throws Exception{
        // forzamos el punto flotante para que la division no sea entera
        float usersInterval = ((float) concurrentUsers/getMaxConcurrentUsers()) * 100;
        if (0 <= usersInterval && usersInterval < 20){
            return "Low";
        } else if (20 <= usersInterval && usersInterval < 80) {
            return "Average";
        } else if (80 <= usersInterval && usersInterval <= 100) {
            return "High";
        } else {
            throw new Exception("[ERROR] The number of concurrent users exceeds the maximum allowed");
        }
    }

    /**
     * Method that calculates the total cost of renewing the domain depending on its registration date, expiration date and the current date when asking.
     * @param date date on which the domain owner wants to know the total cost of renewing the domain name
     * @return zero if isAutoRenew's is false and if it's true, the total cost of renewing the domain.
     * @throws Exception When param "date" is before current date.
     */
    public double predictedRenewalCostByDate(LocalDate date) throws Exception{
        double cost = 0;
        int yearsRegisteredDomain;
        int yearsForExpiration;
        int[] discounts =  {5, 10, 15};

        if (LocalDate.now().isAfter(date)) {
            throw new Exception("[ERROR] The date must be later than the current date.");
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
