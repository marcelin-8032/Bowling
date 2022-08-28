package com.onepoint.bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class BowlingGameTests {

	BowlingGame bowlingGame;

	@BeforeEach
	public void setUp() {
		bowlingGame = new BowlingGame();
	}

	@Test
	void canScoreGame() {
		// test if there is no score at, 10 frames, all zero
		bowlingGame.roll(
				0,0, 0,0,
				0,0, 0,0,
				0,0, 0,0,
				0,0, 0,0,
				0,0, 0,0);
		assertEquals(0, bowlingGame.score());
	}

	@Test
	void canScoreGameOfOnes() {
		// what about if we get 1,1 in each frame, so there result should be 20
		bowlingGame.roll(
				1,1, 1,1,
				1,1, 1,1,
				1,1, 1,1, 
				1,1, 1,1,
				1,1, 1,1);
		assertEquals(20, bowlingGame.score());
	}

	@Test
	void canScoreSpareFollowedByThree() {
		//first frame spare is 10, then 3 in first throw next frame,  so 10+3 for first frame and second frame 3, so the sum is 16
		bowlingGame.roll(
				5,5, 3,0, 
				0,0, 0,0, 
				0,0, 0,0, 
				0,0, 0,0, 
				0,0, 0,0);
		assertEquals(16, bowlingGame.score());
	}

	@Test
	void canScoreStrikeFollowedByThreeThenThree() {
		//first frame strike so 10 is waiting for the next two throws, here we have 3 and 3 so the score first frame is 10+3+3=16, then the second frame has 3+3=6, the total is 16+6=22
		bowlingGame.roll(
				10, 3,3,
				0,0, 0,0,
				0,0, 0,0,
				0,0, 0,0,
				0,0, 0,0);
		assertEquals(22, bowlingGame.score());
	}

	@Test
	void canScoreMaximumScore() {
		// 12 rolls, 12 strike 
		bowlingGame.roll(
				10, 10, 
				10, 10,
				10, 10,
				10, 10,
				10, 10,
				10, 10);
		assertEquals(300, bowlingGame.score());
	}

	@Test
	void canReturn90Score() {
		// 10 paires and 9 miss
		bowlingGame.roll(
				9,0, 9,0,
				9,0, 9,0,
				9,0, 9,0, 
				9,0, 9,0,
				9,0, 9,0);
		assertEquals(90, bowlingGame.score());
	}

	@Test
	void canScoreWhen10PairesAreSpare5WithAfinalBonus5() {
		// 10 paires 5 and spare with a final 5
		bowlingGame.roll(
				5,5, 5,5,
				5,5, 5,5,
				5,5, 5,5,
				5,5, 5,5,
				5,5, 5,5, 5);
		assertEquals(150, bowlingGame.score());
	}

	@Test
	void canScoreWhenLastFrameStrike() {
		// last frame is strike, so there are two bonus
		bowlingGame.roll(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 1,3);
		assertEquals(14, bowlingGame.score());
	}

	
	@Test
	void canScoreWhenLastFrameSpare() {
		// last frame is spare, so there is one bonus
		bowlingGame.roll(
				0,0, 0,0,
				0,0, 0,0,
				0,0, 0,0, 
				0,0, 0,0,
				0,0, 3,7,
				5);
		assertEquals(15, bowlingGame.score());
	}
	
	
	@Test
	void canScoreWhenLastFrameStrikeAndSpareInFifthFrame() {
		bowlingGame.roll(
				5,2 , 3,5,
				5,0 , 4,1,
				9,1 , 5,1,
				7,1 ,  10,
				5,2 ,  10, 
				10  ,   2);
		assertEquals(100, bowlingGame.score());
	}
	
	
	
	

}
