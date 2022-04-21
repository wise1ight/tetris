package org.teamseven.tetris.block.generator;

import org.junit.jupiter.api.Test;
import org.teamseven.tetris.block.Block;

import static org.assertj.core.api.Assertions.assertThat;

class RandomUnitBlockGeneratorTest {

    @Test
    public void 블럭_생성_확인() {
        //given
        BlockGenerator generator = new RandomBlockGenerator();

        //when
        Block block = generator.generate();

        //then
        assertThat(block).isNotNull();
    }
}