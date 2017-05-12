package jdbc;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HibernateDao {

    private List<User> list;
    private List<Sale> orderList;
    private List<Employee> emplist;
    private List<Brand> brandlist;
    private List<Category> catlist;
    private List<Customer> custlist;
    private List<Product> productlist;
    private List<Salary> sallist;
    private List<Sale> salelist;
    private List<Supplier> supplierlist;
    private List<Unit> unitlist;
    private List<Warranty> warrantlist;
    private List<Supplier> suppNamelist;
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean login(User e) {
        try {
            Query query = getSessionFactory().openSession().createQuery("SELECT u FROM User u WHERE u.name=:name AND u.pass =:pass And u.role =:role");
            query.setString("name", e.getName());
            query.setString("pass", e.getPass());
            query.setString("role", e.getRole());
            List<User> cList = query.list();
            cList.toString();

            if (cList.size() > 0) {
                System.out.println("OK");
                return true;
            } else {
                System.out.println("Not OKkkkkk");
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List< Employee> getEmployee() {
        emplist = getSessionFactory().openSession().createCriteria(Employee.class).list();
        return emplist;

    }

    @Transactional
    public void saveEmployee(Employee e) {
        getSessionFactory().getCurrentSession().save(e);
    }

    @Transactional
    public void updateEmployee(Employee e) {
        getSessionFactory().getCurrentSession().update(e);
    }

    @Transactional
    public void deleteEmployee(Employee e) {
        getSessionFactory().getCurrentSession().delete(e);
    }

    public List< Brand> getBrand() {
        brandlist = getSessionFactory().openSession().createCriteria(Brand.class).list();
        return brandlist;

    }

    @Transactional
    public void saveBrand(Brand br) {
        getSessionFactory().getCurrentSession().save(br);
    }

    @Transactional
    public void updateBrand(Brand br) {
        getSessionFactory().getCurrentSession().update(br);
    }

    @Transactional
    public void deleteBrand(Brand br) {
        getSessionFactory().getCurrentSession().delete(br);
    }

    public List<String> getSuppName() {
        Criteria cr = getSessionFactory().openSession().createCriteria(Supplier.class);
        cr.setProjection(Projections.property("suppliername"));
        return cr.list();
    }

    public List<String> getBrandname(String name) {
        Query query = getSessionFactory().openSession().createQuery("Select br.brandname from Brand br where br.suppliername= :name");
        query.setParameter("name", name);
        List results = query.list();
        return results;
    }

    public List<String> getCatName(String name) {
        Query query = getSessionFactory().openSession().createQuery("Select br.catname from Category br where br.brandname= :name");
        query.setParameter("name", name);
        List results = query.list();
        return results;
    }

    public List<String> getUnitName() {
        Criteria cr = getSessionFactory().openSession().createCriteria(Unit.class);
        cr.setProjection(Projections.property("unitname"));
        return cr.list();
    }

    public List<String> getWarrantyName() {
        Criteria cr = getSessionFactory().openSession().createCriteria(Warranty.class);
        cr.setProjection(Projections.property("warrantytype"));
        return cr.list();
    }

    public List<String> getCustomerName() {
        Criteria cr = getSessionFactory().openSession().createCriteria(Customer.class);
        cr.setProjection(Projections.property("custname"));
        return cr.list();
    }

    public List<Warranty> getWarranty() {
        warrantlist = getSessionFactory().openSession().createCriteria(Warranty.class).list();
        return warrantlist;
    }

    @Transactional
    public void saveWarranty(Warranty wr) {
        getSessionFactory().getCurrentSession().save(wr);
    }

    @Transactional
    public void updateWarranty(Warranty wr) {
        getSessionFactory().getCurrentSession().update(wr);
    }

    @Transactional
    public void deleteWarranty(Warranty wr) {
        getSessionFactory().getCurrentSession().delete(wr);
    }

    public List<Unit> getUnit() {
        unitlist = getSessionFactory().openSession().createCriteria(Unit.class).list();
        return unitlist;
    }

    @Transactional
    public void saveUnit(Unit u) {
        getSessionFactory().getCurrentSession().save(u);
    }

    @Transactional
    public void updateUnit(Unit u) {
        getSessionFactory().getCurrentSession().update(u);
    }

    @Transactional
    public void deleteUnit(Unit u) {
        getSessionFactory().getCurrentSession().delete(u);
    }

    public List<Supplier> getSupplier() {
        supplierlist = getSessionFactory().openSession().createCriteria(Supplier.class).list();
        return supplierlist;
    }

    @Transactional
    public void saveSupplier(Supplier sup) {
        getSessionFactory().getCurrentSession().save(sup);
    }

    @Transactional
    public void updateSupplier(Supplier sup) {
        getSessionFactory().getCurrentSession().update(sup);
    }

    @Transactional
    public void deleteSupplier(Supplier sup) {
        getSessionFactory().getCurrentSession().delete(sup);
    }

    public List<Category> getCategory() {
        catlist = getSessionFactory().openSession().createCriteria(Category.class).list();
        return catlist;
    }

    @Transactional
    public void saveCat(Category cat) {
        getSessionFactory().getCurrentSession().save(cat);
    }

    @Transactional
    public void updateCat(Category cat) {
        getSessionFactory().getCurrentSession().update(cat);
    }

    @Transactional
    public void deleteCat(Category cat) {
        getSessionFactory().getCurrentSession().delete(cat);
    }

    public List<Product> getProduct() {
        productlist = getSessionFactory().openSession().createCriteria(Product.class).list();
        return productlist;
    }

    @Transactional
    public void saveProduct(Product pro) {
        getSessionFactory().getCurrentSession().save(pro);
    }

    @Transactional
    public void updateProduct(Product pro) {
        getSessionFactory().getCurrentSession().update(pro);
    }

    @Transactional
    public void deleteProduct(Product pro) {
        getSessionFactory().getCurrentSession().delete(pro);
    }

    public List<Employee> getSingleValue(int id) {
        Query query = getSessionFactory().openSession().createQuery("FROM Employee E WHERE E.id = :id");
        query.setParameter("id", id);
        List results = query.list();
        return results;
    }

    public List<Product> getProductByID(int id) {
        Query query = getSessionFactory().openSession().createQuery("FROM Product P WHERE P.pid = :pid");
        query.setParameter("pid", id);
        List results = query.list();
        return results;
    }

    public List<Salary> getSalary() {
        sallist = getSessionFactory().openSession().createCriteria(Salary.class).list();
        return sallist;
    }

    @Transactional
    public void saveSalary(Salary sal) {
        getSessionFactory().getCurrentSession().save(sal);
    }

    public List<Customer> getCustomer() {
        custlist = getSessionFactory().openSession().createCriteria(Customer.class).list();
        return custlist;
    }

    @Transactional
    public void saveCustomer(Customer cust) {
        getSessionFactory().getCurrentSession().save(cust);
    }

    @Transactional
    public void updateCustomer(Customer cust) {
        getSessionFactory().getCurrentSession().update(cust);
    }

    @Transactional
    public void deleteCustomer(Customer cust) {
        getSessionFactory().getCurrentSession().delete(cust);
    }

    public List<Sale> getSale() {
        salelist = getSessionFactory().openSession().createCriteria(Sale.class).list();
        return salelist;
    }

    public List<Sale> getOrder() {
        Query query = getSessionFactory().openSession().createQuery("FROM Sale order by id desc");
        query.setMaxResults(1);
        orderList = query.list();
        return orderList;
    }

    public int getCountEmployee() {
        Query query = getSessionFactory().openSession().createQuery("select count(*) from Employee");
        return ((Long) query.uniqueResult()).intValue();
    }

    public int getCountSale() {
        Query query = getSessionFactory().openSession().createQuery("select count(*) from Sale");
        return ((Long) query.uniqueResult()).intValue();
    }
    
    public int totalQuantity() {
        Query query = getSessionFactory().openSession().createQuery("Select sum(pro.qty) FROM Product pro");
        return ((Long) query.uniqueResult()).intValue();
    }

    public double totalSale() {
        Criteria criteria = getSessionFactory().openSession().createCriteria(Sale.class);
        criteria.setProjection(Projections.sum("totalprice"));
        Double total = (Double) criteria.uniqueResult();
        return total;
    }

    public int getCountProduct() {
        Query query = getSessionFactory().openSession().createQuery("select count(*) from Product");
        return ((Long) query.uniqueResult()).intValue();
    }

    @Transactional
    public void saveSale(Sale sel) {
        getSessionFactory().getCurrentSession().save(sel);
    }

    @Transactional
    public void updateSale(Sale sel) {
        getSessionFactory().getCurrentSession().update(sel);
    }

    @Transactional
    public void deleteSale(Sale sel) {
        getSessionFactory().getCurrentSession().delete(sel);
    }
    
    @Transactional
    public void saveUser(User br) {
        getSessionFactory().getCurrentSession().save(br);
    }

}
