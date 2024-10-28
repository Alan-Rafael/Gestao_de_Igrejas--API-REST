package com.agenda.agendaLagoinha.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


@DataJpaTest
@ActiveProfiles("test")
class MemberRepositoryTest {

    @Test
    void deleteInBatch() {
    }
}