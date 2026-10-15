package study.lld.projects.ratelimiter;

import static java.lang.Thread.sleep;

import java.time.Duration;
import java.time.Instant;
import lombok.AllArgsConstructor;

/*
Time: 40m
- could design a simple token bucket
Learnings-
- hello interview want to have you design a RateLimiter for a API gateway, default rate limiter will be for all APIS
    and specific endpoint ratelimiter will exist for a particular endpoint. Also, we want all ratelimiters to handle clientId
    as in real world we have limits based on customer.
- mention this will be a middleware in API gateway.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        /*
        Rate limiter
        - We want all incoming apis to be accepted or rejected to regulate load on our service
        - For this we enforce rate limits - example 500 GETs per minute, 10 PUT per minute
        - Our system should be able to register new rate limits, and tell us if the rate limit for a particular
            operation is breached or not.
        - If the rate limit is reached, we will indicate that.
        - The rate limits can be set for a particular time.
        - We will use token bucket strategy to define rate limits, where we will have a collection which has X no of token
            When a request comes in the token bucket is asked to get a token. If there are some tokens then we get the token
            decrease the amount of token in the bucket. When there is no token, then the limit is reached.
        - The token bucket replenishes its tokens after a set Duration.
        - getToken(): boolean returns true if there are token / false if rate limit is reached. We can also return a waitFor,
            to advise the client for how much time they have to wait to try again.
        - createRateLimiter(Duration,requestCount): RateLimiter

        RateLimiter{
            Integer tokenBucket;
            Integer noOfAllowedRequests;
            Duration refillInterval;
            Instant lastRefillTime;

            RateLimiter(int noOfAllowedRequest,Duration refillInterval){
                this.noOfAllowedRequests = noOfAllowedRequest;
                this.refillInterval = refillInterval;
            }
            RateLimitResponse synchronized getToken(){
                refillIfRequired()
                if(tokenBucket>0){
                    tokenBucket--;
                    return successResponse();
                }
                return throttleResponse();
            }
            void refillIfRequired(){
                Instant nextRefillTime=lastRefillTime.plus(refillInterval);
                if(nextRefillTime.isBefore(Instant.now())){
                    tokenBucket=noOfAllowedRequests;
                }
            }
        }
        RateLimitResponse
        - isAllowed:boolean
        - message:String
        - retryAfter:String


         */

        RateLimiterService rateLimiterService = new RateLimiterService();
        RateLimiter getRateLimiter = rateLimiterService.createRateLimiter(1, Duration.ofSeconds(1));
        System.out.println(getRateLimiter.getToken());
        System.out.println(getRateLimiter.getToken());
        System.out.println(getRateLimiter.getToken());
        System.out.println(getRateLimiter.getToken());
        System.out.println(getRateLimiter.getToken());
        System.out.println(getRateLimiter.getToken());
        System.out.println(getRateLimiter.getToken());
        System.out.println(getRateLimiter.getToken());
        System.out.println(getRateLimiter.getToken());
        System.out.println(getRateLimiter.getToken());
        System.out.println(getRateLimiter.getToken());
        sleep(1_000);
        System.out.println(getRateLimiter.getToken());
    }
}

class RateLimiter {
    int id;
    int tokenBucket;
    int noOfAllowedRequests;
    Duration refillInterval;
    Instant lastRefillTime;
    Instant nextRefillTime;

    RateLimiter(int noOfAllowedRequest, Duration refillInterval) {
        if (noOfAllowedRequest <= 0) throw new IllegalArgumentException();

        if (refillInterval.isZero() || refillInterval.isNegative())
            throw new IllegalArgumentException();

        this.noOfAllowedRequests = noOfAllowedRequest;
        this.refillInterval = refillInterval;
        refillBucket(noOfAllowedRequest);
    }

    synchronized RateLimitResponse getToken() {
        refillIfRequired();
        if (tokenBucket > 0) {
            tokenBucket--;
            return successResponse();
        }
        return throttleResponse();
    }

    private RateLimitResponse throttleResponse() {
        return new RateLimitResponse(false, nextRefillTime);
    }

    private RateLimitResponse successResponse() {
        return new RateLimitResponse(true, null);
    }

    void refillIfRequired() {
        if (nextRefillTime.isBefore(Instant.now())) {
            refillBucket(noOfAllowedRequests);
        }
    }

    private void refillBucket(Integer noOfAllowedRequests) {
        tokenBucket = noOfAllowedRequests;
        lastRefillTime = Instant.now();
        nextRefillTime = getNextRefillTime();
    }

    private Instant getNextRefillTime() {
        return lastRefillTime.plus(refillInterval);
    }
}

@AllArgsConstructor
class RateLimitResponse {
    boolean isAllowed;
    Instant waitUntil;

    @Override
    public String toString() {
        String success = "Operation is allowed";
        String failure = "Operation is not allowed retry after " + waitUntil;
        return isAllowed ? success : failure;
    }
}

class RateLimiterService {
    RateLimiter createRateLimiter(int noOfRequests, Duration duration) {
        return new RateLimiter(noOfRequests, duration);
    }
}
