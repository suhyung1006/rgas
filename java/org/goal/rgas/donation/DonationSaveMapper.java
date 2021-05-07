package org.goal.rgas.donation;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author plusperson@gmail.com
 */
@Mapper
public interface DonationSaveMapper {
	public List<DonationSave> list(DonationSave donationSave) throws Exception;

	public void insert(DonationSave donationSave) throws Exception;

	public void update(DonationSave donationSave) throws Exception;
}