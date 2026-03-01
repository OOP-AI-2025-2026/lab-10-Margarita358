package ua.opnu;

import ua.opnu.util.Customer;
import ua.opnu.util.DataProvider;
import ua.opnu.util.Order;
import ua.opnu.util.Product;

import java.time.LocalDate;
import java.util.*;
        import java.util.stream.Collectors;

public class HardTasks {

    private final List<Customer> customers = DataProvider.customers;
    private final List<Order> orders = DataProvider.orders;
    private final List<Product> products = DataProvider.products;

    public static void main(String[] args) {
    }

    // 1
    public List<Product> getBooksWithPrice() {
        return products.stream()
                .filter(p -> p.getCategory().equals("Books"))
                .filter(p -> p.getPrice() > 100)
                .toList();
    }

    // 2
    public List<Order> getOrdersWithBabyProducts() {
        return orders.stream()
                .filter(order ->
                        order.getProducts().stream()
                                .anyMatch(product -> product.getCategory().equals("Baby"))
                )
                .toList();
    }

    // 3 (10% скидка на Toys)
    public List<Product> applyDiscountToToys() {
        return products.stream()
                .filter(p -> p.getCategory().equals("Toys"))
                .map(p -> {
                    p.setPrice(p.getPrice() * 0.9);
                    return p;
                })
                .toList();
    }

    // 4
    public Optional<Product> getCheapestBook() {
        return products.stream()
                .filter(p -> p.getCategory().equals("Books"))
                .min(Comparator.comparing(Product::getPrice));
    }

    // 5 (заказ за последний месяц)
    public List<Order> getRecentOrders() {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);

        return orders.stream()
                .filter(order -> order.getOrderDate().isAfter(oneMonthAgo))
                .toList();
    }

    // 6
    public DoubleSummaryStatistics getBooksStats() {
        return products.stream()
                .filter(p -> p.getCategory().equals("Books"))
                .collect(Collectors.summarizingDouble(Product::getPrice));
    }

    // 7
    public Map<Integer, Integer> getOrdersProductsMap() {
        return orders.stream()
                .collect(Collectors.toMap(
                        Order::getId,
                        order -> order.getProducts().size()
                ));
    }

    // 8
    public Map<String, List<Integer>> getProductsByCategory() {
        return products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.mapping(Product::getId, Collectors.toList())
                ));
    }
}