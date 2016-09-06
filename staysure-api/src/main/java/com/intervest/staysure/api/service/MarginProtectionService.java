package com.intervest.staysure.api.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intervest.staysure.database.model.MarginInfo;
import com.intervest.staysure.database.repository.MarginProtectionRepository;

@Service
public class MarginProtectionService {

    @Autowired
    MarginProtectionRepository marginProtectionRepository;

    public BigDecimal getProtectedMargin(String category, BigDecimal buyPrice, Date priceCalculationDate) {
        BigDecimal marginProtectedPrice = buyPrice;
        MarginInfo selectedMarginInfo = null;
        for (MarginInfo marginInfo : marginProtectionRepository.findAll()) {
            if (category != null && priceCalculationDate != null && category.equals(marginInfo.getCategory())
                    && priceCalculationDate.after(marginInfo.getStartDate())
                    && priceCalculationDate.before(marginInfo.getEndDate())) {
                selectedMarginInfo = marginInfo;
                break;
            }
        }
        if (selectedMarginInfo != null && buyPrice != null) {
            // type = 0 : defined as a value | type = 1 : defined as a percentage
            if (selectedMarginInfo.getType() == 0) {
                marginProtectedPrice = buyPrice.add(selectedMarginInfo.getValue());
            } else {
                marginProtectedPrice = buyPrice.add(buyPrice.multiply(selectedMarginInfo.getValue()));
            }
        }
        return marginProtectedPrice;
    }
}
