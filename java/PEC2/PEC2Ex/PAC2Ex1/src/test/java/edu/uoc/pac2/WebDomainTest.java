package edu.uoc.pac2;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class WebDomainTest {
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUpStreams() {
        restoreStreams();
    }

    @BeforeEach
    public void restoreStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testSetDomain() {
        WebDomain webDomain = new WebDomain("uoc.edu", "Gabriel Ferraté i Pascual", "gferrate@uoc.edu", "Hosting UOC",
                LocalDate.now().minusYears(30), LocalDate.now().plusMonths(6), 10000, true, true);

        assertEquals("uoc.edu", webDomain.getDomain());

        webDomain.setDomain(null);
        assertEquals("[ERROR] Invalid domain format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("uoc.edu", webDomain.getDomain());

        webDomain.setDomain("uoc.ed2u");
        assertEquals("[ERROR] Invalid domain format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("uoc.edu", webDomain.getDomain());

        webDomain.setDomain(" uoc.edu ");
        assertEquals("[ERROR] Invalid domain format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("uoc.edu", webDomain.getDomain());

        webDomain.setDomain("spin_uoc.com");
        assertEquals("[ERROR] Invalid domain format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("uoc.edu", webDomain.getDomain());

        webDomain.setDomain("spin!uoc.com");
        assertEquals("[ERROR] Invalid domain format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("uoc.edu", webDomain.getDomain());

        webDomain.setDomain("uocedu");
        assertEquals("[ERROR] Invalid domain format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("uoc.edu", webDomain.getDomain());

        webDomain.setDomain("123456789");
        assertEquals("[ERROR] Invalid domain format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("uoc.edu", webDomain.getDomain());

        webDomain.setDomain("123456789.123");
        assertEquals("[ERROR] Invalid domain format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("uoc.edu", webDomain.getDomain());

        webDomain.setDomain("uo c.edu");
        assertEquals("[ERROR] Invalid domain format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("uoc.edu", webDomain.getDomain());

        webDomain.setDomain("uoc.es");
        assertEquals("[ERROR] Invalid domain format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("uoc.edu", webDomain.getDomain());

        webDomain.setDomain("uoc.cat");
        assertEquals("[ERROR] Invalid domain format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("uoc.edu", webDomain.getDomain());

        webDomain.setDomain("cv.uoc.edu");
        assertEquals("[ERROR] Invalid domain format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("uoc.edu", webDomain.getDomain());

        webDomain.setDomain("UOC.EDU");
        assertEquals("uoc.edu", webDomain.getDomain());

        webDomain.setDomain("uoc.com");
        assertEquals("uoc.com", webDomain.getDomain());

        webDomain.setDomain("uoc.org");
        assertEquals("uoc.org", webDomain.getDomain());

        webDomain.setDomain("uoc.net");
        assertEquals("uoc.net", webDomain.getDomain());

        webDomain.setDomain("uoc.int");
        assertEquals("uoc.int", webDomain.getDomain());

        webDomain.setDomain("uoc.gov");
        assertEquals("uoc.gov", webDomain.getDomain());

        webDomain.setDomain("uoc.mil");
        assertEquals("uoc.mil", webDomain.getDomain());

        webDomain.setDomain("custom-domain-123.com");
        assertEquals("custom-domain-123.com", webDomain.getDomain());
    }

    @Test
    public void testSetOwnerName() {
        WebDomain webDomain = new WebDomain("uoc.edu", "Gabriel Ferraté i Pascual", "gferrate@uoc.edu", "Hosting UOC",
                LocalDate.now().minusYears(30), LocalDate.now().plusMonths(6), 10000, true, true);

        assertEquals("Gabriel Ferraté i Pascual", webDomain.getOwnerName());

        webDomain.setOwnerName(null);
        assertEquals("[ERROR] Owner name cannot be empty.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Gabriel Ferraté i Pascual", webDomain.getOwnerName());

        webDomain.setOwnerName("    ");
        assertEquals("[ERROR] Owner name cannot be empty.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Gabriel Ferraté i Pascual", webDomain.getOwnerName());

        webDomain.setOwnerName("Another name");
        assertEquals("Another name", webDomain.getOwnerName());

        webDomain.setOwnerName(" Another name ");
        assertEquals("Another name", webDomain.getOwnerName());

        webDomain.setOwnerName("G@br13l F3rr@t3");
        assertEquals("G@br13l F3rr@t3", webDomain.getOwnerName());
    }

    @Test
    public void testSetOwnerEmail() {
        WebDomain webDomain = new WebDomain("uoc.edu", "Gabriel Ferraté i Pascual", "gferrate@uoc.edu", "Hosting UOC",
                LocalDate.now().minusYears(30), LocalDate.now().plusMonths(6), 10000, true, true);

        assertEquals("gferrate@uoc.edu", webDomain.getOwnerEmail());

        webDomain.setOwnerEmail(null);
        assertEquals("[ERROR] Invalid owner email format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("gferrate@uoc.edu", webDomain.getOwnerEmail());

        webDomain.setOwnerEmail("");
        assertEquals("[ERROR] Invalid owner email format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("gferrate@uoc.edu", webDomain.getOwnerEmail());

        webDomain.setOwnerEmail("  ");
        assertEquals("[ERROR] Invalid owner email format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("gferrate@uoc.edu", webDomain.getOwnerEmail());

        webDomain.setOwnerEmail("email");
        assertEquals("[ERROR] Invalid owner email format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("gferrate@uoc.edu", webDomain.getOwnerEmail());

        webDomain.setOwnerEmail("sd51f5_uoc");
        assertEquals("[ERROR] Invalid owner email format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("gferrate@uoc.edu", webDomain.getOwnerEmail());

        webDomain.setOwnerEmail("gfer?rate@uoc.edu");
        assertEquals("[ERROR] Invalid owner email format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("gferrate@uoc.edu", webDomain.getOwnerEmail());

        webDomain.setOwnerEmail("gferraté@uoc.edu");
        assertEquals("[ERROR] Invalid owner email format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("gferrate@uoc.edu", webDomain.getOwnerEmail());

        webDomain.setOwnerEmail("gferr ate@uoc.edu");
        assertEquals("[ERROR] Invalid owner email format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("gferrate@uoc.edu", webDomain.getOwnerEmail());

        webDomain.setOwnerEmail("gferrate@uoc");
        assertEquals("[ERROR] Invalid owner email format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("gferrate@uoc.edu", webDomain.getOwnerEmail());

        webDomain.setOwnerEmail("gferrate@uoc.");
        assertEquals("[ERROR] Invalid owner email format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("gferrate@uoc.edu", webDomain.getOwnerEmail());

        webDomain.setOwnerEmail("gferrate@uoc.e");
        assertEquals("[ERROR] Invalid owner email format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("gferrate@uoc.edu", webDomain.getOwnerEmail());

        webDomain.setOwnerEmail("gferrate@spin_uoc.com");
        assertEquals("[ERROR] Invalid owner email format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("gferrate@uoc.edu", webDomain.getOwnerEmail());

        webDomain.setOwnerEmail("gferrate@spin-uoc.com");
        assertEquals("gferrate@spin-uoc.com", webDomain.getOwnerEmail());

        webDomain.setOwnerEmail("gferrate1994@uoc.edu");
        assertEquals("gferrate1994@uoc.edu", webDomain.getOwnerEmail());

        webDomain.setOwnerEmail("g-fErrAte_1994@uoc.edu");
        assertEquals("g-fErrAte_1994@uoc.edu", webDomain.getOwnerEmail());

        webDomain.setOwnerEmail("g.ferrate@uoc.edu");
        assertEquals("g.ferrate@uoc.edu", webDomain.getOwnerEmail());
    }

    @Test
    public void testSetHostingProvider() {
        WebDomain webDomain = new WebDomain("uoc.edu", "Gabriel Ferraté i Pascual", "gferrate@uoc.edu", "Hosting UOC",
                LocalDate.now().minusYears(30), LocalDate.now().plusMonths(6), 10000, true, true);

        assertEquals("Hosting UOC", webDomain.getHostingProvider());

        webDomain.setHostingProvider(null);
        assertEquals("[ERROR] Hosting provider cannot be empty.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Hosting UOC", webDomain.getHostingProvider());

        webDomain.setHostingProvider("    ");
        assertEquals("[ERROR] Hosting provider cannot be empty.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Hosting UOC", webDomain.getHostingProvider());

        webDomain.setHostingProvider("Another name");
        assertEquals("Another name", webDomain.getHostingProvider());

        webDomain.setHostingProvider(" Another name ");
        assertEquals("Another name", webDomain.getHostingProvider());

        webDomain.setHostingProvider("H0st1ng U0C");
        assertEquals("H0st1ng U0C", webDomain.getHostingProvider());
    }

    @Test
    public void testSetRegistrationDate() {
        WebDomain webDomain = new WebDomain("uoc.edu", "Gabriel Ferraté i Pascual", "gferrate@uoc.edu", "Hosting UOC",
                LocalDate.now().minusYears(30), LocalDate.now().plusMonths(6), 10000, true, true);

        assertEquals(LocalDate.now().minusYears(30), webDomain.getRegistrationDate());

        LocalDate date = LocalDate.now();

        webDomain.setRegistrationDate(date);
        assertEquals(date, webDomain.getRegistrationDate());

        webDomain.setRegistrationDate(LocalDate.of(2013,12,21));
        assertEquals(LocalDate.of(2013,12,21),webDomain.getRegistrationDate());

        webDomain.setRegistrationDate(null);
        assertEquals("[ERROR] The registration date must be prior or equal to the current date.",
                outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals(LocalDate.of(2013,12,21),webDomain.getRegistrationDate());

        webDomain.setRegistrationDate(LocalDate.now().plusYears(10));
        assertEquals("[ERROR] The registration date must be prior or equal to the current date.",
                outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals(LocalDate.of(2013,12,21),webDomain.getRegistrationDate());

        webDomain.setRegistrationDate(LocalDate.now().plusDays(1));
        assertEquals("[ERROR] The registration date must be prior or equal to the current date.",
                outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals(LocalDate.of(2013,12,21),webDomain.getRegistrationDate());

        date = LocalDate.now().minusDays(1);

        webDomain.setRegistrationDate(date);
        assertEquals(date,webDomain.getRegistrationDate());
    }

    @Test
    public void testSetExpirationDate() {
        WebDomain webDomain = new WebDomain("uoc.edu", "Gabriel Ferraté i Pascual", "gferrate@uoc.edu", "Hosting UOC",
                LocalDate.now().minusYears(30), LocalDate.now().plusMonths(6), 10000, true, true);

        assertEquals(LocalDate.now().plusMonths(6), webDomain.getExpirationDate());

        LocalDate date = LocalDate.now();

        webDomain.setExpirationDate(date);
        assertEquals(date, webDomain.getExpirationDate());

        webDomain.setExpirationDate(LocalDate.of(3013,12,21));
        assertEquals(LocalDate.of(3013,12,21),webDomain.getExpirationDate());

        webDomain.setExpirationDate(null);
        assertEquals("[ERROR] The expiration date must be later than the registration date.",
                outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals(LocalDate.of(3013,12,21),webDomain.getExpirationDate());

        webDomain.setExpirationDate(LocalDate.now().minusYears(31));
        assertEquals("[ERROR] The expiration date must be later than the registration date.",
                outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals(LocalDate.of(3013,12,21),webDomain.getExpirationDate());

        webDomain.setExpirationDate(webDomain.getRegistrationDate().plusYears(1));
        assertEquals(webDomain.getRegistrationDate().plusYears(1),webDomain.getExpirationDate());

        webDomain.setExpirationDate(webDomain.getRegistrationDate().minusDays(1));
        assertEquals("[ERROR] The expiration date must be later than the registration date.",
                outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals(webDomain.getRegistrationDate().plusYears(1),webDomain.getExpirationDate());
    }

    @Test
    public void testMaxConcurrentUsers() {
        WebDomain webDomain = new WebDomain("uoc.edu", "Gabriel Ferraté i Pascual", "gferrate@uoc.edu", "Hosting UOC",
                LocalDate.now().minusYears(30), LocalDate.now().plusMonths(6), 10000, true, true);

        assertEquals(10000, webDomain.getMaxConcurrentUsers());

        webDomain.setMaxConcurrentUsers(0);
        assertEquals("[ERROR] Maximum concurrent users must be greater than 0.",
                outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals(10000, webDomain.getMaxConcurrentUsers());

        webDomain.setMaxConcurrentUsers(-1);
        assertEquals("[ERROR] Maximum concurrent users must be greater than 0.",
                outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals(10000, webDomain.getMaxConcurrentUsers());

        webDomain.setMaxConcurrentUsers(1);
        assertEquals(1, webDomain.getMaxConcurrentUsers());

        webDomain.setMaxConcurrentUsers(999);
        assertEquals(999, webDomain.getMaxConcurrentUsers());
    }

    @Test
    public void testIsWhoisPrivacy() {
        WebDomain webDomain = new WebDomain("uoc.edu", "Gabriel Ferraté i Pascual", "gferrate@uoc.edu", "Hosting UOC",
                LocalDate.now().minusYears(30), LocalDate.now().plusMonths(6), 10000, true, true);

        assertTrue(webDomain.isWhoisPrivacy());

        webDomain.setWhoisPrivacy(false);
        assertFalse(webDomain.isWhoisPrivacy());

        webDomain.setWhoisPrivacy(true);
        assertTrue(webDomain.isWhoisPrivacy());
    }

    @Test
    public void testIsAutoRenew() {
        WebDomain webDomain = new WebDomain("uoc.edu", "Gabriel Ferraté i Pascual", "gferrate@uoc.edu", "Hosting UOC",
                LocalDate.now().minusYears(30), LocalDate.now().plusMonths(6), 10000, true, true);

        assertTrue(webDomain.isAutoRenew());

        webDomain.setAutoRenew(false);
        assertFalse(webDomain.isAutoRenew());

        webDomain.setAutoRenew(true);
        assertTrue(webDomain.isAutoRenew());
    }

    @Test
    public void testIsExpired() {
        WebDomain webDomain = new WebDomain("uoc.edu", "Gabriel Ferraté i Pascual", "gferrate@uoc.edu", "Hosting UOC",
                LocalDate.now().minusYears(30), LocalDate.now().plusMonths(6), 10000, true, true);

        assertFalse(webDomain.isExpired());

        webDomain.setExpirationDate(LocalDate.now().minusDays(1));
        assertTrue(webDomain.isExpired());

        webDomain.setExpirationDate(LocalDate.now().plusDays(1));
        assertFalse(webDomain.isExpired());

        webDomain.setExpirationDate(LocalDate.now());
        assertFalse(webDomain.isExpired());
    }

    @Test
    public void testIsAboutToExpire() {
        WebDomain webDomain = new WebDomain("uoc.edu", "Gabriel Ferraté i Pascual", "gferrate@uoc.edu", "Hosting UOC",
                LocalDate.now().minusYears(30), LocalDate.now().plusMonths(6), 10000, true, true);

        assertFalse(webDomain.isAboutToExpire());

        webDomain.setExpirationDate(LocalDate.now().plusDays(29));
        assertTrue(webDomain.isAboutToExpire());

        webDomain.setExpirationDate(LocalDate.now());
        assertTrue(webDomain.isAboutToExpire());

        webDomain.setExpirationDate(LocalDate.now().plusMonths(1));
        assertFalse(webDomain.isAboutToExpire());

        webDomain.setExpirationDate(LocalDate.now().plusYears(1));
        assertFalse(webDomain.isAboutToExpire());
    }

    @Test
    public void testDomainConcurrenceStatus() {
        WebDomain webDomain = new WebDomain("uoc.edu", "Gabriel Ferraté i Pascual", "gferrate@uoc.edu", "Hosting UOC",
                LocalDate.now().minusYears(30), LocalDate.now().plusMonths(6), 10000, true, true);

        assertEquals("Low", webDomain.domainConcurrenceStatus(1000));

        webDomain.setMaxConcurrentUsers(1000);
        assertEquals("High", webDomain.domainConcurrenceStatus(1000));

        assertEquals("High", webDomain.domainConcurrenceStatus(800));
        assertEquals("Average", webDomain.domainConcurrenceStatus(799));
    }

    @Test
    public void testPredictedRenewalCostByDate() {
        WebDomain webDomain = new WebDomain("uoc.edu", "Gabriel Ferraté i Pascual", "gferrate@uoc.edu", "Hosting UOC",
                LocalDate.now().minusYears(30), LocalDate.now().plusMonths(6), 10000, true, true);

        assertEquals(0.0, webDomain.predictedRenewalCostByDate(LocalDate.now().plusMonths(6)));
        assertEquals(84.92, (double) Math.round(webDomain.predictedRenewalCostByDate(LocalDate.now().plusYears(10)) * 100) / 100);

        webDomain.setRegistrationDate(LocalDate.now().minusYears(1).plusDays(1));
        webDomain.setExpirationDate(LocalDate.now().plusDays(1));
        assertEquals(28.97, (double) Math.round(webDomain.predictedRenewalCostByDate(LocalDate.now().plusYears(3)) * 100) / 100);
        assertEquals(28.97, (double) Math.round(webDomain.predictedRenewalCostByDate(LocalDate.now().plusYears(3).plusDays(1)) * 100) / 100);
        assertEquals(37.96, (double) Math.round(webDomain.predictedRenewalCostByDate(LocalDate.now().plusYears(3).plusDays(2)) * 100) / 100);
        assertEquals(55.44, (double) Math.round(webDomain.predictedRenewalCostByDate(LocalDate.now().plusYears(6)) * 100) / 100);
        assertEquals(55.44, (double) Math.round(webDomain.predictedRenewalCostByDate(LocalDate.now().plusYears(6).plusDays(1)) * 100) / 100);
        assertEquals(63.94, (double) Math.round(webDomain.predictedRenewalCostByDate(LocalDate.now().plusYears(6).plusDays(2)) * 100) / 100);
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    void checkFieldsSanity() {
        //check attribute fields
        assertEquals(11, WebDomain.class.getDeclaredFields().length);
        try {
            assertTrue(Modifier.isPrivate(WebDomain.class.getDeclaredField("domain").getModifiers()));
            assertTrue(Modifier.isPrivate(WebDomain.class.getDeclaredField("ownerName").getModifiers()));
            assertTrue(Modifier.isPrivate(WebDomain.class.getDeclaredField("ownerEmail").getModifiers()));
            assertTrue(Modifier.isPrivate(WebDomain.class.getDeclaredField("hostingProvider").getModifiers()));
            assertTrue(Modifier.isPrivate(WebDomain.class.getDeclaredField("registrationDate").getModifiers()));
            assertTrue(Modifier.isPrivate(WebDomain.class.getDeclaredField("expirationDate").getModifiers()));
            assertTrue(Modifier.isPrivate(WebDomain.class.getDeclaredField("maxConcurrentUsers").getModifiers()));
            assertTrue(Modifier.isPrivate(WebDomain.class.getDeclaredField("whoisPrivacy").getModifiers()));
            assertTrue(Modifier.isPrivate(WebDomain.class.getDeclaredField("autoRenew").getModifiers()));

            assertTrue(Modifier.isPrivate(WebDomain.class.getDeclaredField("validExtensions").getModifiers()));
            assertTrue(Modifier.isStatic(WebDomain.class.getDeclaredField("validExtensions").getModifiers()));
            assertTrue(Modifier.isPrivate(WebDomain.class.getDeclaredField("renewalCost").getModifiers()));
            assertTrue(Modifier.isStatic(WebDomain.class.getDeclaredField("renewalCost").getModifiers()));
        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    void checkMethodsSanity() {
        //check constructors
        assertEquals(1, WebDomain.class.getDeclaredConstructors().length);

        try {
            Class<WebDomain> webDomain = WebDomain.class;
            Constructor<WebDomain> constructor = webDomain.getConstructor(String.class, String.class, String.class, String.class,
                    LocalDate.class, LocalDate.class, int.class, boolean.class, boolean.class);

            assertNotNull(constructor);
            assertTrue(Modifier.isPublic(constructor.getModifiers()));

            Class<?>[] parameterTypes = constructor.getParameterTypes();
            assertArrayEquals(new Class<?>[]{
                    String.class, String.class, String.class, String.class,
                    LocalDate.class, LocalDate.class, int.class, boolean.class, boolean.class
            }, parameterTypes);
        } catch (NoSuchMethodException e) {
            fail("There is some problem with the definition of constructors");
        }

        //check methods, parameters and return types
        try {
            assertTrue(Modifier.isPublic(WebDomain.class.getDeclaredMethod("getDomain").getModifiers()));
            assertEquals(String.class, WebDomain.class.getDeclaredMethod("getDomain").getReturnType());
            assertTrue(Modifier.isPublic(WebDomain.class.getDeclaredMethod("setDomain", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the domain attribute");
        }

        try {
            assertTrue(Modifier.isPrivate(WebDomain.class.getDeclaredMethod("isValidDomain", String.class).getModifiers()));
            assertEquals(boolean.class, WebDomain.class.getDeclaredMethod("isValidDomain", String.class).getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of isValidDomain method");
        }

        try {
            assertTrue(Modifier.isPublic(WebDomain.class.getDeclaredMethod("getOwnerEmail").getModifiers()));
            assertEquals(String.class, WebDomain.class.getDeclaredMethod("getOwnerEmail").getReturnType());
            assertTrue(Modifier.isPublic(WebDomain.class.getDeclaredMethod("setOwnerEmail", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the ownerEmail attribute");
        }

        try {
            assertTrue(Modifier.isPublic(WebDomain.class.getDeclaredMethod("getHostingProvider").getModifiers()));
            assertEquals(String.class, WebDomain.class.getDeclaredMethod("getHostingProvider").getReturnType());
            assertTrue(Modifier.isPublic(WebDomain.class.getDeclaredMethod("setHostingProvider", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the hostingProvider attribute");
        }

        try {
            assertTrue(Modifier.isPublic(WebDomain.class.getDeclaredMethod("getRegistrationDate").getModifiers()));
            assertEquals(LocalDate.class, WebDomain.class.getDeclaredMethod("getRegistrationDate").getReturnType());
            assertTrue(Modifier.isPublic(WebDomain.class.getDeclaredMethod("setRegistrationDate", LocalDate.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the registrationDate attribute");
        }

        try {
            assertTrue(Modifier.isPublic(WebDomain.class.getDeclaredMethod("getExpirationDate").getModifiers()));
            assertEquals(LocalDate.class, WebDomain.class.getDeclaredMethod("getExpirationDate").getReturnType());
            assertTrue(Modifier.isPublic(WebDomain.class.getDeclaredMethod("setExpirationDate", LocalDate.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the expirationDate attribute");
        }

        try {
            assertTrue(Modifier.isPublic(WebDomain.class.getDeclaredMethod("getMaxConcurrentUsers").getModifiers()));
            assertEquals(int.class, WebDomain.class.getDeclaredMethod("getMaxConcurrentUsers").getReturnType());
            assertTrue(Modifier.isPublic(WebDomain.class.getDeclaredMethod("setMaxConcurrentUsers", int.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the maxConcurrentUsers attribute");
        }

        try {
            assertTrue(Modifier.isPublic(WebDomain.class.getDeclaredMethod("isWhoisPrivacy").getModifiers()));
            assertEquals(boolean.class, WebDomain.class.getDeclaredMethod("isWhoisPrivacy").getReturnType());
            assertTrue(Modifier.isPublic(WebDomain.class.getDeclaredMethod("setWhoisPrivacy", boolean.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the whoisPrivacy attribute");
        }

        try {
            assertTrue(Modifier.isPublic(WebDomain.class.getDeclaredMethod("isAutoRenew").getModifiers()));
            assertEquals(boolean.class, WebDomain.class.getDeclaredMethod("isAutoRenew").getReturnType());
            assertTrue(Modifier.isPublic(WebDomain.class.getDeclaredMethod("setAutoRenew", boolean.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the autoRenew attribute");
        }

        try {
            assertTrue(Modifier.isPublic(WebDomain.class.getDeclaredMethod("isAutoRenew").getModifiers()));
            assertEquals(boolean.class, WebDomain.class.getDeclaredMethod("isAutoRenew").getReturnType());
            assertTrue(Modifier.isPublic(WebDomain.class.getDeclaredMethod("setAutoRenew", boolean.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the autoRenew attribute");
        }

        try {
            assertTrue(Modifier.isPublic(WebDomain.class.getDeclaredMethod("isExpired").getModifiers()));
            assertEquals(boolean.class, WebDomain.class.getDeclaredMethod("isExpired").getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of isExpired method");
        }

        try {
            assertTrue(Modifier.isPublic(WebDomain.class.getDeclaredMethod("isAboutToExpire").getModifiers()));
            assertEquals(boolean.class, WebDomain.class.getDeclaredMethod("isAboutToExpire").getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of isAboutToExpire method");
        }

        try {
            assertTrue(Modifier.isPublic(WebDomain.class.getDeclaredMethod("domainConcurrenceStatus", int.class).getModifiers()));
            assertEquals(String.class, WebDomain.class.getDeclaredMethod("domainConcurrenceStatus", int.class).getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of domainConcurrenceStatus method");
        }

        try {
            assertTrue(Modifier.isPublic(WebDomain.class.getDeclaredMethod("predictedRenewalCostByDate", LocalDate.class).getModifiers()));
            assertEquals(double.class, WebDomain.class.getDeclaredMethod("predictedRenewalCostByDate", LocalDate.class).getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of predictedRenewalCostByDate method");
        }
    }
}
