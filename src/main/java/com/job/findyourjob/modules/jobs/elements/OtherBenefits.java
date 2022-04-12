package com.job.findyourjob.modules.jobs.elements;

import com.job.findyourjob.modules.jobs.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OtherBenefits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;


    public OtherBenefits(String s){
        this.value= s;
    }
}
