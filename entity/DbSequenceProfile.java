package com.example.profileservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="profile_sequence")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbSequenceProfile {
    @Id
    private String profileId;
    private int seq;
    public String getProfileId() {
        return profileId;
    }
    public void setProfileId(String id) {this.profileId = profileId;}
    public int getSeq() {
        return seq;
    }
    public void setSeq(int seq) {
        this.seq = seq;
    }

}
