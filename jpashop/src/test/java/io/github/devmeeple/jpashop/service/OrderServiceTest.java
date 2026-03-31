package io.github.devmeeple.jpashop.service;

import io.github.devmeeple.jpashop.domain.Address;
import io.github.devmeeple.jpashop.domain.Member;
import io.github.devmeeple.jpashop.domain.Order;
import io.github.devmeeple.jpashop.domain.OrderStatus;
import io.github.devmeeple.jpashop.domain.item.Book;
import io.github.devmeeple.jpashop.domain.item.Item;
import io.github.devmeeple.jpashop.exception.NotEnoughStockException;
import io.github.devmeeple.jpashop.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    @DisplayName("상품을 주문하면 주문 정보가 생성되고 재고가 감소한다.")
    void givenMemberAndItemAndOrderCount_whenOrder_thenCreateOrderAndDecreaseStock() {
        // given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10); // 이름, 가격, 재고
        int orderCount = 2;

        // when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        // then
        Order getOrder = orderRepository.findOne(orderId);

        assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(getOrder.getOrderItems()).hasSize(1);
        assertThat(getOrder.getTotalPrice()).isEqualTo(20_000);
        assertThat(item.getStockQuantity()).isEqualTo(8);
    }

    @Test
    @DisplayName("재고보다 많은 수량을 주문하면 예외가 발생한다.")
    void givenOrderQuantityExceedsStock_whenOrder_thenThrowNotEnoughStockException() {
        // given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10_000, 10); // 이름, 가격, 재고

        int orderCount = 11; // 재고보다 많은 수량

        // when & then
        assertThatThrownBy(() -> orderService.order(member.getId(), item.getId(), orderCount))
                .isInstanceOf(NotEnoughStockException.class)
                .hasMessage("need more stock");
    }

    @Test
    @DisplayName("주문을 취소하면 주문 상태가 CANCEL로 변경되고 재고가 복구된다.")
    void givenOrderedItem_whenCancelOrder_thenStatusIsCancelAndStockRestored() {
        // give
        Member member = createMember();
        Item item = createBook("시골 JPA", 10_000, 10); // 이름, 가격, 재고
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        // when
        orderService.cancelOrder(orderId);

        // then
        Order getOrder = orderRepository.findOne(orderId);

        assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(item.getStockQuantity()).isEqualTo(10);
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        em.persist(book);
        return book;
    }
}
