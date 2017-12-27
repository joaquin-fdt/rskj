/*
 * This file is part of RskJ
 * Copyright (C) 2017 RSK Labs Ltd.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package co.rsk.test.builderstest;

import co.rsk.test.World;
import co.rsk.test.builders.AccountBuilder;
import org.ethereum.core.Account;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

/**
 * Created by ajlopez on 8/7/2016.
 */
public class AccountBuilderTest {
    @Test
    public void createAccountWithNameAsSeed() {
        Account account = new AccountBuilder().name("acc1").build();

        Assert.assertNotNull(account);
        Assert.assertTrue(account.getEcKey().hasPrivKey());
    }

    @Test
    public void createAccountWithBalanceAndCode() {
        World world = new World();

        byte[] code = new byte[] { 0x01, 0x02, 0x03 };
        BigInteger balance = BigInteger.TEN;

        Account account = new AccountBuilder(world).name("acc1")
                .balance(BigInteger.TEN)
                .code(code)
                .build();

        Assert.assertNotNull(account);
        Assert.assertTrue(account.getEcKey().hasPrivKey());
        Assert.assertEquals(balance, world.getRepository().getBalance(account.getAddress().getBytes()));
        Assert.assertArrayEquals(code, world.getRepository().getCode(account.getAddress().getBytes()));
    }
}
