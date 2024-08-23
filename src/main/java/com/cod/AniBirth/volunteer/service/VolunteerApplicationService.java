package com.cod.AniBirth.volunteer.service;

import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.volunteer.entity.Volunteer;
import com.cod.AniBirth.volunteer.entity.VolunteerApplication;
import com.cod.AniBirth.volunteer.repository.VolunteerApplicationRepository;
import com.cod.AniBirth.volunteer.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VolunteerApplicationService {
    private final VolunteerApplicationRepository volunteerApplicationRepository;
    private final VolunteerRepository volunteerRepository;

    public List<VolunteerApplication> getAll() {
        return volunteerApplicationRepository.findAll();
    }

    public List<VolunteerApplication> getAllById(Long id) {
        return volunteerApplicationRepository.findByVolunteerId(id);
    }

    public void create(Member member, Volunteer volunteer) {
        VolunteerApplication volunteerApplication = VolunteerApplication.builder()
                .member(member)
                .volunteer(volunteer)
                .build();

        volunteer.setApplicant(volunteer.getApplicant() + 1);

        volunteerApplicationRepository.save(volunteerApplication);
        volunteerRepository.save(volunteer);
    }

    public boolean existsByMemberAndVolunteer(Member member, Volunteer volunteer) {
        return volunteerApplicationRepository.existsByMemberAndVolunteer(member, volunteer);
    }

    public List<VolunteerApplication> getAllByMember(Member member) {
        return volunteerApplicationRepository.findByMember(member);
    }

    public void delete(Member member, Volunteer volunteer) {
        VolunteerApplication volunteerApplication = volunteerApplicationRepository.findByMemberAndVolunteer(member, volunteer);

        volunteerApplicationRepository.delete(volunteerApplication);
        volunteer.setApplicant(volunteer.getApplicant() - 1);

        volunteerRepository.save(volunteer);
    }

    public List<VolunteerApplication> findByMember(Member member) {
        return volunteerApplicationRepository.findByMember(member);
    }
}
