package com.softdesign.devintensive.data.storage.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.softdesign.devintensive.data.network.res.UserListRes;

import java.util.ArrayList;
import java.util.List;

public class UserDTO implements Parcelable {
    private String mPhoto;
    private String mFullName;
    private String mRating;
    private String mCodeLines;
    private String mProject;
    private String mBio;
    private List<String> mRepositories;

    public UserDTO(UserListRes.Datum userData) {
        List<String> list = new ArrayList<>();
        for (UserListRes.Repo repo : userData.getRepositories().getRepo()) {
            list.add(repo.getGit());
        }
        mPhoto = userData.getPublicInfo().getPhoto();
        mFullName = userData.getFullName();
        mRating = String.valueOf(userData.getProfileValues().getRait());
        mCodeLines = String.valueOf(userData.getProfileValues().getLinesCode());
        mProject = String.valueOf(userData.getProfileValues().getProjects());
        mBio = userData.getPublicInfo().getBio();
        mRepositories = list;
    }


    public String getPhoto() {
        return mPhoto;
    }

    public String getFullName() {
        return mFullName;
    }

    public String getRating() {
        return mRating;
    }

    public String getCodeLines() {
        return mCodeLines;
    }

    public String getProject() {
        return mProject;
    }

    public String getBio() {
        return mBio;
    }

    public List<String> getRepositories() {
        return mRepositories;
    }

    protected UserDTO(Parcel in) {
        mPhoto = in.readString();
        mFullName = in.readString();
        mRating = in.readString();
        mCodeLines = in.readString();

        mProject = in.readString();
        mBio = in.readString();
        if (in.readByte() == 0x01) {
            mRepositories = new ArrayList<String>();
            in.readList(mRepositories, String.class.getClassLoader());
        } else {
            mRepositories = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPhoto);
        dest.writeString(mFullName);
        dest.writeString(mRating);
        dest.writeString(mCodeLines);
        dest.writeString(mProject);
        dest.writeString(mBio);
        if (mRepositories == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(mRepositories);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UserDTO> CREATOR = new Parcelable.Creator<UserDTO>() {
        @Override
        public UserDTO createFromParcel(Parcel in) {
            return new UserDTO(in);
        }

        @Override
        public UserDTO[] newArray(int size) {
            return new UserDTO[size];
        }
    };
}