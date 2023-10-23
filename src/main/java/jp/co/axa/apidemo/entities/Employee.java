package jp.co.axa.apidemo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name="EMPLOYEE")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="EMPLOYEE_NAME")
    private String name;


    @Column(name="EMPLOYEE_SALARY")
    private Integer salary;


    @Column(name="DEPARTMENT")
    private String department;

}
