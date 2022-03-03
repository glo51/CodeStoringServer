package platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.models.Code;
import platform.repositories.CodeRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class CodeService {

    @Autowired
    CodeRepository codeRepository;

    public String save(Code code) {
        codeRepository.save(code);
        return code.getId();
    }

    public List<Code> latestTenCodes() {
        return codeRepository.latestTenCodes();
    }

    public Code getById(String id) throws NoSuchElementException {

        Code code = codeRepository.findById(id).orElseThrow();

        int remainingViews = code.getViews();
        if (code.isViewsRestriction()) {
            code.setViews(--remainingViews);
            if (remainingViews == 0) {
                delete(code);
            } else {
                codeRepository.save(code);
            }
        }

        long remainingTime = code.getTime();
        if (code.isTimeRestriction()) {
            LocalDateTime expirationDateTime = code.getRawLocalDateTime().plusSeconds(remainingTime);
            if (LocalDateTime.now().isAfter(expirationDateTime)) {
                delete(code);
                throw new NoSuchElementException();
            }
            remainingTime = LocalDateTime.now().until(expirationDateTime, ChronoUnit.SECONDS);
            code.setTime(remainingTime);
        }

        return code;
    }

    private void delete(Code code) {
        codeRepository.delete(code);
    }
}
