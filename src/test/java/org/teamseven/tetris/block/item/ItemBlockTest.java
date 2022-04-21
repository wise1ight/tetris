package org.teamseven.tetris.block.item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.teamseven.tetris.block.Block;
import org.teamseven.tetris.block.IBlock;
import org.teamseven.tetris.block.UnitBlock;

import static org.assertj.core.api.Assertions.assertThat;

class ItemBlockTest {

    @Test
    @DisplayName("폭탄 블럭 생성 테스트")
    void boom_block_test() {
        Block Iblock = new IBlock();
        UnitBlock boomBlock = new BoomBlock();
        InnerItemBlock itemBlock = new InnerItemBlock(Iblock.getShape(), boomBlock);
        boolean flag = false;

        for (int j = 0; j < itemBlock.height(); j++) {
            for (int i = 0; i < itemBlock.width(); i++) {
                if (itemBlock.getUnitBlock(i, j) == boomBlock) {
                    flag = true;
                }
            }
        }
        assertThat(flag).isTrue();
    }

    @Test
    @DisplayName("라인 삭제 블럭 생성 테스트")
    void line_remove_block_test() {
        Block Iblock = new IBlock();
        UnitBlock lineRemoveBlock = new LineRemoveBlock();
        InnerItemBlock itemBlock = new InnerItemBlock(Iblock.getShape(), lineRemoveBlock);
        boolean flag = false;

        for (int j = 0; j < itemBlock.height(); j++) {
            for (int i = 0; i < itemBlock.width(); i++) {
                if (itemBlock.getUnitBlock(i, j) == lineRemoveBlock) {
                    flag = true;
                }
            }
        }
        assertThat(flag).isTrue();
    }
}