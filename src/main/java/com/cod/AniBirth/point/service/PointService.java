package com.cod.AniBirth.point.service;

import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.point.entity.Point;
import com.cod.AniBirth.point.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {
    private final PointRepository pointRepository;

    public Point getOrCreatePoint(Member member) {
        return pointRepository.findByMemberId(member.getId())
                .orElseGet(() -> {
                    Point newPoint = Point.builder()
                            .member(member)
                            .balance(0)
                            .build();
                    return pointRepository.save(newPoint);
                });
    }

    public void rechargePoints(Member member, int amount) {
        Point point = getOrCreatePoint(member);
        point.setBalance(point.getBalance() + amount);
        pointRepository.save(point);
    }

    public void save(Point point) {
        pointRepository.save(point);
    }

    public int getBalance(Member member) {
        Point point = getOrCreatePoint(member);
        return point.getBalance();
    }
}
