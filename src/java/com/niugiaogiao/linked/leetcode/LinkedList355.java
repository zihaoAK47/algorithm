package com.niugiaogiao.linked.leetcode;

import java.util.*;

/**
 * @author zi hao
 * @version 1.0
 * @date 2022-06-13 10:35
 */
class Twitter {

    static final Map<Integer, LinkedList<Integer>> twitterInfo = new HashMap<>(16);

    static final Map<Integer, LinkedList<Integer>> twitterFollow = new HashMap<>(16);

    public Twitter() {
    }

    public void postTweet(int userId, int tweetId) {
        LinkedList<Integer> tweets = twitterInfo.get(userId);
        if (tweets == null) {
            tweets = new LinkedList<>();
            tweets.addFirst(tweetId);
            twitterInfo.put(userId, tweets);
        } else {
            tweets.addFirst(tweetId);
        }
    }

    // 返回 tweetId
    public List<Integer> getNewsFeed(int userId) {
        // 获取订阅的用户列表数据
        LinkedList<Integer> follows = twitterFollow.get(userId);
        // 将当前用户加入到订阅列表中
        if (follows == null) {
            follows = new LinkedList<>();
            follows.addFirst(userId);
        } else {
            boolean flag = true;
            for (Integer item : follows) {
                if (item == userId) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                follows.addFirst(userId);
            }
        }

        // 获取 10 条数据
        int messageIndex = 10;
        List<Integer> result = new ArrayList<>(messageIndex);
        Map<Integer, Integer> cache = new HashMap<>();

        while (messageIndex != 0) {
            int minTwitterId = -1;
            int chooseUserId = -1;
            for (Integer userItemInfo : follows) {
                LinkedList<Integer> userSendTwitter = twitterInfo.get(userItemInfo);
                if (userSendTwitter == null) {
                    continue;
                }

                Integer cachePosition = cache.get(userItemInfo);
                if (cachePosition == null) {
                    cachePosition = 0;
                    cache.put(userItemInfo, cachePosition);
                }

                if (cachePosition >= userSendTwitter.size()) {
                    continue;
                }

                Integer twitterId = userSendTwitter.get(cachePosition);
                if (twitterId > minTwitterId) {
                    minTwitterId = twitterId;
                    chooseUserId = userItemInfo;
                }
            }

            if (minTwitterId == -1) {
                return result;
            }
            cache.put(chooseUserId, cache.get(chooseUserId) + 1);
            result.add(minTwitterId);
            messageIndex--;
        }

        return result;
    }

    public void follow(int followerId, int followeeId) {
        LinkedList<Integer> followData = twitterFollow.get(followerId);
        if (followData == null) {
            followData = new LinkedList<>();
            followData.addFirst(followeeId);
            twitterFollow.put(followerId, followData);
        } else {
            followData.addFirst(followeeId);
        }
    }

    public void unfollow(int followerId, int followeeId) {
        LinkedList<Integer> followData = twitterFollow.get(followerId);
        if (followData != null) {
            for (int i = 0; i < followData.size(); i++) {
                if (followData.get(i) == followeeId) {
                    followData.remove(i);
                    return;
                }
            }
        }
    }
}

public class LinkedList355 {

    static Twitter twitter = new Twitter();

    public static void main(String[] args) {
    }
}
