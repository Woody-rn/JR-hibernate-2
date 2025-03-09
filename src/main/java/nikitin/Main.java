package nikitin;

import nikitin.config.HibernateConfiguration;
import nikitin.dao.*;
import nikitin.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private final SessionFactory sessionFactory;

    private final ActorDao actorDao;
    private final AddressDao addressDao;
    private final CategoryDao categoryDao;
    private final CityDao cityDao;
    private final CountryDao countryDao;
    private final CustomerDao customerDao;
    private final FilmDao filmDao;
    private final FilmTextDao filmTextDao;
    private final InventoryDao inventoryDao;
    private final LanguageDao languageDao;
    private final PaymentDao paymentDao;
    private final RentalDao rentalDao;
    private final StaffDao staffDao;
    private final StoreDao storeDao;

    public Main() {
        Configuration config = HibernateConfiguration.get();
        sessionFactory = config.buildSessionFactory();

        actorDao = new ActorDao(sessionFactory);
        addressDao = new AddressDao(sessionFactory);
        categoryDao = new CategoryDao(sessionFactory);
        cityDao = new CityDao(sessionFactory);
        countryDao = new CountryDao(sessionFactory);
        customerDao = new CustomerDao(sessionFactory);
        filmDao = new FilmDao(sessionFactory);
        filmTextDao = new FilmTextDao(sessionFactory);
        inventoryDao = new InventoryDao(sessionFactory);
        languageDao = new LanguageDao(sessionFactory);
        paymentDao = new PaymentDao(sessionFactory);
        rentalDao = new RentalDao(sessionFactory);
        staffDao = new StaffDao(sessionFactory);
        storeDao = new StoreDao(sessionFactory);
    }

    public static void main(String[] args) {
        Main main = new Main();
        Customer customer = main.createCustomer();
        main.returnInventoryToStore();
        main.rentOneInventoryItem(customer);
        main.addNewFilmReleaseEvent();

    }

    private void addNewFilmReleaseEvent() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Language language = languageDao.getItems(0, 10).stream()
                    .unordered()
                    .findAny()
                    .orElseThrow();
            Set<Category> categories = new HashSet<>(categoryDao.getItems(0, 10));
            Set<Actor> actors = new HashSet<>(actorDao.getItems(0, 10));
            Set<SpecialFeature> features = Set.of(SpecialFeature.COMMENTARIES, SpecialFeature.TRAILERS);

            Film film = new Film();
            film.setLanguage(language);
            film.setOriginalLanguage(language);
            film.setActors(actors);
            film.setCategories(categories);
            film.setSpecialFeatures(features);
            film.setDescription("test film Description");
            film.setLength((short) 20);
            film.setTitle("test film Title");
            film.setRentalDuration((byte) 10);
            film.setRating(Rating.R);
            film.setReleaseYear(Year.now());
            film.setReplacementCost(BigDecimal.TWO);
            film.setRentalRate(BigDecimal.ZERO);
            filmDao.save(film);

            FilmText filmText = new FilmText(film);
            filmTextDao.save(filmText);

            session.getTransaction().commit();
        }
    }

    private void rentOneInventoryItem(Customer customer) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Film film = filmDao.getAnyAvailableFilmForRental();
            Store store = getStoreForExample();

            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(store);
            inventoryDao.save(inventory);

            Staff staff = store.getStaff();

            Rental rental = new Rental();
            rental.setCustomer(customer);
            rental.setInventory(inventory);
            rental.setStaff(staff);
            rental.setRentalDate(LocalDateTime.now());
            rentalDao.save(rental);

            Payment payment = new Payment();
            payment.setCustomer(customer);
            payment.setRental(rental);
            payment.setStaff(staff);
            payment.setAmount(BigDecimal.valueOf(105.77d));
            paymentDao.save(payment);

            session.getTransaction().commit();
        }
    }


    private Customer createCustomer() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Customer customer = new Customer();
            customer.setFirstName("John");
            customer.setLastName("Smith");
            customer.setEmail("john.smith@gmail.com");
            customer.setIsActive(true);

            Store store = getStoreForExample();
            Address address = createAddress();
            customer.setAddress(address);
            customer.setStore(store);
            customerDao.save(customer);

            session.getTransaction().commit();
            return customer;
        }
    }

    private Address createAddress() {
        City city = cityDao.findByName("Kaliningrad");
        Address address = new Address();
        address.setAddress("1633 Broadway");
        address.setDistrict("Buffalo");
        address.setCity(city);
        address.setPhone("(212) 274-7500");
        addressDao.save(address);
        return address;
    }

    private void returnInventoryToStore() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Rental itemToRental = rentalDao.getAnyRentedItem();
            itemToRental.setReturnDate(LocalDateTime.now());
            rentalDao.save(itemToRental);

            session.getTransaction().commit();
        }
    }

    private Store getStoreForExample() {
        return storeDao.getItems(0, 1).getFirst();
    }
}