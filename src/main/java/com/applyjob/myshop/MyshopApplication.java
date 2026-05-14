package com.applyjob.myshop;

import com.applyjob.myshop.entity.Order;
import com.applyjob.myshop.entity.OrderItem;
import com.applyjob.myshop.entity.Product;
import com.applyjob.myshop.repository.OrderRepository;
import com.applyjob.myshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class MyshopApplication {

	private final ProductRepository productRepository;
	private final OrderRepository orderRepository;

	public static void main(String[] args) {
		SpringApplication.run(MyshopApplication.class, args);
	}

	@Bean
	CommandLineRunner initData() {

		return args -> {

			if (productRepository.count() > 0) {
				return;
			}

			List<Product> products = List.of(

					Product.builder()
							.name("iPhone 15")
							.price(BigDecimal.valueOf(25000000))
							.quantity(10)
							.description("Apple smartphone")
							.build(),

					Product.builder()
							.name("Samsung Galaxy S24")
							.price(BigDecimal.valueOf(22000000))
							.quantity(15)
							.description("Samsung flagship")
							.build(),

					Product.builder()
							.name("Macbook Pro M3")
							.price(BigDecimal.valueOf(45000000))
							.quantity(5)
							.description("Apple laptop")
							.build(),

					Product.builder()
							.name("AirPods Pro")
							.price(BigDecimal.valueOf(6000000))
							.quantity(20)
							.description("Wireless earbuds")
							.build(),

					Product.builder()
							.name("iPad Air")
							.price(BigDecimal.valueOf(18000000))
							.quantity(8)
							.description("Apple tablet")
							.build(),

					Product.builder()
							.name("Dell XPS 15")
							.price(BigDecimal.valueOf(40000000))
							.quantity(7)
							.description("Dell premium laptop")
							.build(),

					Product.builder()
							.name("Logitech MX Master 3S")
							.price(BigDecimal.valueOf(2500000))
							.quantity(30)
							.description("Wireless mouse")
							.build(),

					Product.builder()
							.name("Sony WH-1000XM5")
							.price(BigDecimal.valueOf(9000000))
							.quantity(12)
							.description("Noise cancelling headphones")
							.build(),

					Product.builder()
							.name("Apple Watch Series 9")
							.price(BigDecimal.valueOf(12000000))
							.quantity(14)
							.description("Apple smartwatch")
							.build(),

					Product.builder()
							.name("Asus ROG Strix")
							.price(BigDecimal.valueOf(35000000))
							.quantity(6)
							.description("Gaming laptop")
							.build(),

					Product.builder()
							.name("Mechanical Keyboard")
							.price(BigDecimal.valueOf(3000000))
							.quantity(25)
							.description("RGB mechanical keyboard")
							.build(),

					Product.builder()
							.name("27 Inch Monitor")
							.price(BigDecimal.valueOf(7000000))
							.quantity(11)
							.description("4K monitor")
							.build(),

					Product.builder()
							.name("Chuot may tinh Logitech")
							.price(BigDecimal.valueOf(700000))
							.quantity(0)
							.description("Super click")
							.active(false)
							.build()
			);

			productRepository.saveAll(products);

			Product iphone = products.get(0);
			Product samsung = products.get(1);
			Product macbook = products.get(2);

			Order order1 = Order.builder()
					.customerName("Nguyen Van A")
					.customerPhone("0123456789")
					.customerAddress("Ho Chi Minh City")
					.createdAt(LocalDateTime.now())
					.items(new ArrayList<>())
					.build();

			OrderItem item1 = OrderItem.builder()
					.product(iphone)
					.price(iphone.getPrice())
					.quantity(2)
					.order(order1)
					.build();

			OrderItem item2 = OrderItem.builder()
					.product(samsung)
					.price(samsung.getPrice())
					.quantity(1)
					.order(order1)
					.build();

			order1.getItems().add(item1);
			order1.getItems().add(item2);

			order1.setTotalPrice(
					item1.getPrice().multiply(BigDecimal.valueOf(item1.getQuantity()))
							.add(
									item2.getPrice().multiply(BigDecimal.valueOf(item2.getQuantity()))
							)
			);

			Order order2 = Order.builder()
					.customerName("Tran Thi B")
					.customerPhone("0987654321")
					.customerAddress("Ha Noi")
					.createdAt(LocalDateTime.now())
					.items(new ArrayList<>())
					.build();

			OrderItem item3 = OrderItem.builder()
					.product(macbook)
					.price(macbook.getPrice())
					.quantity(1)
					.order(order2)
					.build();

			order2.getItems().add(item3);

			order2.setTotalPrice(
					item3.getPrice().multiply(BigDecimal.valueOf(item3.getQuantity()))
			);

			orderRepository.saveAll(List.of(order1, order2));

			System.out.println("Sample data initialized");
		};
	}
}