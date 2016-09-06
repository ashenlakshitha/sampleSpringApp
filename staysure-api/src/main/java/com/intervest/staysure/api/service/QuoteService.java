package com.intervest.staysure.api.service;

import com.intervest.staysure.database.model.Quote;
import com.intervest.staysure.database.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by charith on 9/4/16.
 */
@Service
@Transactional
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    public void saveQuote(Quote quote) {
        quoteRepository.save(quote);
    }

    public List<Quote> getAllQuotes() {
        return quoteRepository.findAllByOrderByStatusDesc();
    }
}
