package com.nv.youNeverWait.analatic.pl;


import java.util.List;

import com.nv.youNeverWait.analatic.bl.Measure;
import com.nv.youNeverWait.analatic.bl.impl.BirthWeight;
import com.nv.youNeverWait.analatic.bl.impl.MaternalMortalityMorbidity;
import com.nv.youNeverWait.analatic.pl.entity.ApgarScoreEntity;
import com.nv.youNeverWait.analatic.pl.entity.BirthWeightEntity;
import com.nv.youNeverWait.analatic.pl.entity.BloodGroupEntity;
import com.nv.youNeverWait.analatic.pl.entity.BloodLossEntity;
import com.nv.youNeverWait.analatic.pl.entity.BodyMassIndexEntity;
import com.nv.youNeverWait.analatic.pl.entity.BookedStatisticsEntity;
import com.nv.youNeverWait.analatic.pl.entity.CaesareanSectionEntity;
import com.nv.youNeverWait.analatic.pl.entity.EpisiotomyEntity;
import com.nv.youNeverWait.analatic.pl.entity.FetalComplexitesEntity;
import com.nv.youNeverWait.analatic.pl.entity.FourthStageEntity;
import com.nv.youNeverWait.analatic.pl.entity.InductionEntity;
import com.nv.youNeverWait.analatic.pl.entity.IntravenusFluidEntity;
import com.nv.youNeverWait.analatic.pl.entity.MaternalAgeEntity;
import com.nv.youNeverWait.analatic.pl.entity.MaternalComplicationsEntity;
import com.nv.youNeverWait.analatic.pl.entity.MaternalHeightEntity;
import com.nv.youNeverWait.analatic.pl.entity.MaternalMortalityMorbidityEntity;
import com.nv.youNeverWait.analatic.pl.entity.MaternalWeightEntity;
import com.nv.youNeverWait.analatic.pl.entity.OxyTocicEntity;
import com.nv.youNeverWait.analatic.pl.entity.ParityEntity;
import com.nv.youNeverWait.analatic.pl.entity.PerinealTearEntity;
import com.nv.youNeverWait.analatic.pl.entity.PlacentalWtEntity;
import com.nv.youNeverWait.analatic.pl.entity.PresentationEntity;
import com.nv.youNeverWait.analatic.pl.entity.PreviousCSEntity;
import com.nv.youNeverWait.analatic.pl.entity.RobsonClassEntity;
import com.nv.youNeverWait.analatic.pl.entity.ThirdStageEntity;
import com.nv.youNeverWait.analatic.pl.entity.VaginalDeliveryEntity;
import com.nv.youNeverWait.pl.dao.GenericDao;

public interface AnalaticDao extends GenericDao {
	
	public List<MaternalAgeEntity> getMaternalAgeInferences(Integer fmonth,Integer fyear,Integer tomonth,Integer toyear);
	public List<MaternalAgeEntity> getMaternalAgeInferences(Integer fmonth,Integer fyear,Integer tomonth,Integer toyear,Integer hospital);
	
	public List<MaternalHeightEntity> getMaternalHeightInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
	public List<MaternalHeightEntity> getMaternalHeightInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
	
	public List<MaternalWeightEntity> getMaternalWeightInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
	public List<MaternalWeightEntity> getMaternalWeightInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
	
	
	
	public List<MaternalComplicationsEntity> getMaternalComplicationsInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
	public List<MaternalComplicationsEntity> getMaternalComplicationsInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
	

	
	public List<ParityEntity> getParityInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
	public List<ParityEntity> getParityInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
	
	public List<PlacentalWtEntity> getPlacentalWtInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
	public List<PlacentalWtEntity> getPlacentalWtInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
	
	public List<PreviousCSEntity> getPrevCsInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
	public List<PreviousCSEntity> getPrevCsInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
	
	public List<RobsonClassEntity> getRobsonClassInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
	public List<RobsonClassEntity> getRobsonClassInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear, Integer hospital);
	
	public List<ThirdStageEntity> getTSDInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
	public List<ThirdStageEntity> getTSDInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
	
	public List<VaginalDeliveryEntity> getVaginalDeliveryInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
	public List<VaginalDeliveryEntity> getVaginalDeliveryInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
	

    
    public List<ApgarScoreEntity> getApgarScoreInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
    public List<ApgarScoreEntity> getApgarScoreInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
 
    public List<BloodGroupEntity> getBloodGroupInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
    public List<BloodGroupEntity> getBloodGroupInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
    
    public List<BloodLossEntity> getBloodLossInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
    public List<BloodLossEntity> getBloodLossInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
    
    public List<BodyMassIndexEntity> getBMIInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
    public List<BodyMassIndexEntity> getBMIInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
    
    public List<CaesareanSectionEntity> getCaesareanSectionInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
    public List<CaesareanSectionEntity> getCaesareanSectionInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
    
    public List<BookedStatisticsEntity> getBookedStatInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
    public List<BookedStatisticsEntity> getBookedStatInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
    
    
    public List<OxyTocicEntity> getOxyTocicInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
    public List<OxyTocicEntity> getOxyTocicInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
    
    
    public List<FourthStageEntity> getFSDInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
    public List<FourthStageEntity> getFSDInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
    
  
    
    public List<InductionEntity> getInductionInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
    public List<InductionEntity> getInductionInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
    
	public List<PresentationEntity> getPresentationInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
	public List<PresentationEntity> getPresentationInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
	
	public List<EpisiotomyEntity> getEpisiotomyInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
	public List<EpisiotomyEntity> getEpisiotomyInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
	
	public List<PerinealTearEntity> getPerinealTearInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
	public List<PerinealTearEntity> getPerinealTearInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
	
	public List<FetalComplexitesEntity> getFetalComplexitesInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
	public List<FetalComplexitesEntity> getFetalComplexitesInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
	
	public List<BirthWeightEntity> getBirthWeightInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
	public List<BirthWeightEntity> getBirthWeightInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
	
	public List<MaternalMortalityMorbidityEntity> getMaternalMortalityMorbidityInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
	public List<MaternalMortalityMorbidityEntity> getMaternalMortalityMorbidityInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
	
	public List<IntravenusFluidEntity> getIvFluidInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear);
	public List<IntravenusFluidEntity> getIvFluidInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital);
	
}
