package com.yieldstreet.accreditation.repository;

import com.yieldstreet.accreditation.model.Accreditation;
import com.yieldstreet.accreditation.model.AccreditationStatus;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class AccreditationRepository {

//    private final Map<String, Accreditation> accreditations = new HashMap<>();
    private final List<Accreditation> accreditations = new LinkedList<>();

    public void saveAccreditation(Accreditation accreditation) {
        accreditations.add(accreditation);
    }

    public Accreditation findAccreditation(String id) {
        return accreditations.stream()
                .filter(accreditation -> (accreditation.getAccreditationId().equals(id)))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No match found!"));
    }

    public boolean doesExistPendingAccreditationForUser(String userId) {
        for (Accreditation a : accreditations) {
            if (a.getUserId().equals(userId) && a.getStatus() == AccreditationStatus.PENDING) {
                return true;
            }
        }
        return false;
    }

}
