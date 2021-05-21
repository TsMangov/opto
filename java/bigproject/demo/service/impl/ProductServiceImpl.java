package bigproject.demo.service.impl;

import bigproject.demo.model.entities.CartItemEntity;
import bigproject.demo.model.entities.CategoryEntity;
import bigproject.demo.model.entities.ProductEntity;
import bigproject.demo.model.entities.TopicEntity;
import bigproject.demo.model.entities.enums.GenderEnum;
import bigproject.demo.model.service.ProductServiceModel;
import bigproject.demo.repository.CartItemRepository;
import bigproject.demo.repository.ProductRepository;
import bigproject.demo.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final CartItemRepository cartItemRepository;
    //todo fix repositories and serivices
    private final CategoryService categoryService;
    private final CloudinaryService cloudinaryService;
    private final TopicService topicService;


    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository, UserService userService, CartItemRepository cartItemRepository, CategoryService categoryService, CloudinaryService cloudinaryService, TopicService topicService) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.userService = userService;
        this.cartItemRepository = cartItemRepository;
        this.categoryService = categoryService;
        this.cloudinaryService = cloudinaryService;
        this.topicService = topicService;
    }



    @Override
    public List<ProductEntity> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void addProductToUsersCart(Long id, String username) {
        //todo make it with serviceModel
        //todo handle throw
        ProductEntity product = this.productRepository.findById(id).orElseThrow();
        List<CartItemEntity> list = this.cartItemRepository.findAllByUserEntity_Username(username);
        boolean alreadyIn = false;
        for (CartItemEntity cartItemEntity : list) {
            if( cartItemEntity.getProduct().getId().equals( id ) && cartItemEntity.getOrderEntity() == null ){
                cartItemEntity.setCount(cartItemEntity.getCount() + 1);
                cartItemRepository.save(cartItemEntity);
                alreadyIn = true;
            }
        }
        if(!alreadyIn){
            CartItemEntity cartItem = new CartItemEntity().setCount(1).setProduct(product).setUserEntity(this.userService.getUserByUsername(username));
            this.cartItemRepository.save(cartItem);
//            System.out.println();

        }

    }

    @Override
    public List<ProductEntity> getAllByCategoryId(Long id) {
        return this.productRepository.findAllByCategory_Id(id);
    }

    @Override
    public void addNewProduct(ProductServiceModel model) throws IOException {
        MultipartFile img = model.getImgInput();
        String imageUrl = cloudinaryService.uploadImage(img);
        CategoryEntity category = this.categoryService.findByName(model.getCategory());
        ProductEntity product = modelMapper.map(model, ProductEntity.class);
        product.setImgUrl(imageUrl);
        product.setCategory(category);
        productRepository.save(product);
//        System.out.println();
    }

    //todo nov kod tuka
    @Override
    public void addProductToUsersCartAndWantedTopic(Long id, String username, Long topicId) {
        //todo make it with serviceModel
        //todo handle throw
        ProductEntity product = this.productRepository.findById(id).orElseThrow();
        List<CartItemEntity> list = this.cartItemRepository.findAllByUserEntity_Username(username);
        TopicEntity topic = this.topicService.findTopicById(topicId);
        boolean alreadyIn = false;
        for (CartItemEntity cartItemEntity : list) {
            if( cartItemEntity.getProduct().getId().equals( id ) && cartItemEntity.getOrderEntity() == null && cartItemEntity.getTopicEntity().equals(topic) ){
                cartItemEntity.setCount(cartItemEntity.getCount() + 1);
                cartItemRepository.save(cartItemEntity);
                alreadyIn = true;
            }
        }
        if(!alreadyIn){
            CartItemEntity cartItem = new CartItemEntity().setCount(1).setProduct(product).setUserEntity(this.userService.getUserByUsername(username)).setTopicEntity(topic);
            this.cartItemRepository.save(cartItem);
//            System.out.println();

        }
    }


    @Override
    public void initProducts() {
        if(productRepository.count()==0){
            //kucheta

            ProductEntity productEntity = new ProductEntity()
                    .setName("Koko")
                    .setBreed("Pekines")
                    .setGender(GenderEnum.MALES)
                    .setBirthDate(LocalDate.of(2020,10,22))
                    .setPrice(BigDecimal.valueOf(3432))
                    .setCategory(categoryService.findByName("Dogs"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616675552/initPicture/kuche/%D0%BF%D0%B5%D0%BA%D0%B8%D0%BD%D0%B5%D1%81_bktu3l.jpg");
            this.productRepository.save(productEntity);
            productEntity = new ProductEntity()
                    .setName("Lucky")
                    .setBirthDate(LocalDate.of(2020,4,12))
                    .setPrice(BigDecimal.valueOf(1233))
                    .setGender(GenderEnum.MALES)
                    .setBreed("Golden retriver")
                    .setCategory(categoryService.findByName("Dogs"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616675552/initPicture/kuche/%D0%B3%D0%BE%D0%BB%D0%B4%D1%8A%D0%BD_%D1%80%D0%B5%D1%82%D1%80%D0%B8%D0%B2%D1%8A%D1%80_evqd3t.jpg");
            this.productRepository.save(productEntity);
            productEntity = new ProductEntity()
                    .setName("Gosho")
                    .setBirthDate(LocalDate.of(2020,6,14))
                    .setPrice(BigDecimal.valueOf(1233))
                    .setGender(GenderEnum.FEMALE)
                    .setBreed("Bigal")
                    .setCategory(categoryService.findByName("Dogs"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616675552/initPicture/kuche/%D0%B1%D0%B8%D0%B3%D1%8A%D0%BB_sk90dw.jpg");
            this.productRepository.save(productEntity);
            productEntity = new ProductEntity()
                    .setName("Sonya")
                    .setBirthDate(LocalDate.of(2020,12,28))
                    .setPrice(BigDecimal.valueOf(411))
                    .setGender(GenderEnum.FEMALE)
                    .setBreed("Nemska ovcharka")
                    .setCategory(categoryService.findByName("Dogs"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616675552/initPicture/kuche/%D0%BD%D0%B5%D0%BC%D1%81%D0%BA%D0%B0_%D0%BE%D0%B2%D1%87%D0%B0%D1%80%D0%BA%D0%B0_rhx63d.jpg");
            this.productRepository.save(productEntity);
            productEntity = new ProductEntity()
                    .setName("Hacki")
                    .setBirthDate(LocalDate.of(2020,7,9))
                    .setPrice(BigDecimal.valueOf(122.5))
                    .setGender(GenderEnum.MALES)
                    .setBreed("Akita anu")
                    .setCategory(categoryService.findByName("Dogs"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616675552/initPicture/kuche/%D0%B0%D0%BA%D0%B8%D1%82%D0%B0_%D0%B8%D0%BD%D1%83_xwevnm.jpg");
            this.productRepository.save(productEntity);
            productEntity = new ProductEntity()
                    .setName("Camila")
                    .setBirthDate(LocalDate.of(2020,8,14))
                    .setPrice(BigDecimal.valueOf(122.5))
                    .setGender(GenderEnum.FEMALE)
                    .setBreed("Chao Chao")
                    .setCategory(categoryService.findByName("Dogs"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616675489/initPicture/kuche/%D1%87%D0%B0%D0%BE_%D1%87%D0%B0%D0%BE_ku1tsf.jpg");
            this.productRepository.save(productEntity);

            // kotki
            productEntity = new ProductEntity()
                    .setName("Mara")
                    .setBirthDate(LocalDate.of(2020,4,12))
                    .setPrice(BigDecimal.valueOf(122.5))
                    .setGender(GenderEnum.FEMALE)
                    .setBreed("Yaponski bobtail")
                    .setCategory(categoryService.findByName("Cats"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616675539/initPicture/kotki/%D0%AF%D0%BF%D0%BE%D0%BD%D1%81%D0%BA%D0%B8_%D0%B1%D0%BE%D0%B1%D1%82%D0%B5%D0%B9%D0%BB_folxgi.jpg");
            this.productRepository.save(productEntity);
            productEntity = new ProductEntity()
                    .setName("Snow")
                    .setBirthDate(LocalDate.of(2020,4,12))
                    .setPrice(BigDecimal.valueOf(122.5))
                    .setGender(GenderEnum.MALES)
                    .setBreed("Sibirska kotka")
                    .setCategory(categoryService.findByName("Cats"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616675384/initPicture/kotki/%D0%A1%D0%B8%D0%B1%D0%B8%D1%80%D1%81%D0%BA%D0%B0_%D0%BA%D0%BE%D1%82%D0%BA%D0%B0_nh3mws.jpg");
            this.productRepository.save(productEntity);
            productEntity = new ProductEntity()
                    .setName("Roza")
                    .setBirthDate(LocalDate.of(2020,3,22))
                    .setPrice(BigDecimal.valueOf(122.5))
                    .setGender(GenderEnum.FEMALE)
                    .setBreed("Siamska kotka")
                    .setCategory(categoryService.findByName("Cats"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616675384/initPicture/kotki/%D1%81%D0%B8%D0%B0%D0%BC%D1%81%D0%BA%D0%B0_%D0%BA%D0%BE%D1%82%D0%BA%D0%B0_wu4tq6.jpg");
            this.productRepository.save(productEntity);
            productEntity = new ProductEntity()
                    .setName("Puffy")
                    .setBirthDate(LocalDate.of(2021,2,18))
                    .setPrice(BigDecimal.valueOf(122.5))
                    .setGender(GenderEnum.MALES)
                    .setBreed("Persiiska kotka malka")
                    .setCategory(categoryService.findByName("Cats"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616675359/initPicture/kotki/%D0%9F%D0%B5%D1%80%D1%81%D0%B8%D0%B9%D1%81%D0%BA%D0%B0_%D0%BA%D0%BE%D1%82%D0%BA%D0%B0_%D0%BC%D0%B0%D0%BB%D0%BA%D0%B0_t81jah.jpg");
            this.productRepository.save(productEntity);
            productEntity = new ProductEntity()
                    .setName("Mini")
                    .setBirthDate(LocalDate.of(2020,11,29))
                    .setPrice(BigDecimal.valueOf(122.5))
                    .setGender(GenderEnum.FEMALE)
                    .setBreed("Persiiska kotka golyama")
                    .setCategory(categoryService.findByName("Cats"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616675357/initPicture/kotki/%D0%BF%D0%B5%D1%80%D1%81%D0%B8%D0%B9%D1%81%D0%BA%D0%B0_%D0%BA%D0%BE%D1%82%D0%BA%D0%B0_%D0%B3%D0%BE%D0%BB%D1%8F%D0%BC%D0%B0_j3ubze.jpg");
            this.productRepository.save(productEntity);
            productEntity = new ProductEntity()
                    .setName("Faraon")
                    .setBirthDate(LocalDate.of(2020,4,12))
                    .setPrice(BigDecimal.valueOf(122.5))
                    .setGender(GenderEnum.MALES)
                    .setBreed("Kanadski sfinks")
                    .setCategory(categoryService.findByName("Cats"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616675350/initPicture/kotki/%D0%9A%D0%B0%D0%BD%D0%B0%D0%B4%D1%81%D0%BA%D0%B8_%D1%81%D1%84%D0%B8%D0%BD%D0%BA%D1%81_kw8vad.jpg");
            this.productRepository.save(productEntity);
            productEntity = new ProductEntity()
                    .setName("Mila")
                    .setBirthDate(LocalDate.of(2020,12,12))
                    .setPrice(BigDecimal.valueOf(122.5))
                    .setGender(GenderEnum.FEMALE)
                    .setBreed("Britanska kasokosmesta")
                    .setCategory(categoryService.findByName("Cats"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616675341/initPicture/kotki/%D0%91%D1%80%D0%B8%D1%82%D0%B0%D0%BD%D1%81%D0%BA%D0%B0_%D0%BA%D1%8A%D1%81%D0%BE%D0%BA%D0%BE%D1%81%D0%BC%D0%B5%D1%81%D1%82%D0%B0_%D0%BA%D0%BE%D1%82%D0%BA%D0%B0_wvcfuo.jpg");
            this.productRepository.save(productEntity);

            //ribki
            productEntity = new ProductEntity()
                    .setName("Riko")
                    .setBirthDate(LocalDate.of(2020,12,12))
                    .setPrice(BigDecimal.valueOf(122.5))
                    .setGender(GenderEnum.MALES)
                    .setBreed("Zlatna ribka")
                    .setCategory(categoryService.findByName("Fish"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616675465/initPicture/ribi/%D0%97%D0%BB%D0%B0%D1%82%D0%BD%D0%B0_%D1%80%D0%B8%D0%B1%D0%BA%D0%B0_wbngor.jpg");
            this.productRepository.save(productEntity);
            productEntity = new ProductEntity()
                    .setName("Kiro")
                    .setBirthDate(LocalDate.of(2020,12,12))
                    .setPrice(BigDecimal.valueOf(122.5))
                    .setGender(GenderEnum.MALES)
                    .setBreed("Riba Papagal")
                    .setCategory(categoryService.findByName("Fish"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616675464/initPicture/ribi/%D1%80%D0%B8%D0%B1%D0%B0_%D0%BF%D0%B0%D0%BF%D0%B0%D0%B3%D0%B0%D0%BB_2_wa0ajo.jpg");
            this.productRepository.save(productEntity);
            productEntity = new ProductEntity()
                    .setName("Roki")
                    .setBirthDate(LocalDate.of(2020,12,12))
                    .setPrice(BigDecimal.valueOf(122.5))
                    .setGender(GenderEnum.MALES)
                    .setBreed("Riba Papagal")
                    .setCategory(categoryService.findByName("Fish"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616675464/initPicture/ribi/%D1%80%D0%B8%D0%B1%D0%B0_%D0%BF%D0%B0%D0%BF%D0%B0%D0%B3%D0%B0%D0%BB_1_egurrc.jpg");
            this.productRepository.save(productEntity);
            productEntity = new ProductEntity()
                    .setName("Silvia")
                    .setBirthDate(LocalDate.of(2020,12,12))
                    .setPrice(BigDecimal.valueOf(122.5))
                    .setGender(GenderEnum.FEMALE)
                    .setBreed("Skalariya")
                    .setCategory(categoryService.findByName("Fish"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616675504/initPicture/ribi/%D1%81%D0%BA%D0%B0%D0%BB%D0%B0%D1%80%D0%B8%D1%8F_jorwmy.jpg");
            this.productRepository.save(productEntity);
            productEntity = new ProductEntity()
                    .setName("Riko")
                    .setBirthDate(LocalDate.of(2020,12,12))
                    .setPrice(BigDecimal.valueOf(122.5))
                    .setGender(GenderEnum.FEMALE)
                    .setBreed("Heler")
                    .setCategory(categoryService.findByName("Fish"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616675465/initPicture/ribi/%D0%A5%D0%B5%D0%BB%D0%B5%D1%80_k6s9lm.jpg");
            this.productRepository.save(productEntity);

            // ptici

            productEntity = new ProductEntity()
                    .setName("Dovecio")
                    .setBirthDate(LocalDate.of(2020,12,12))
                    .setPrice(BigDecimal.valueOf(122.5))
                    .setGender(GenderEnum.MALES)
                    .setBreed("Dove")
                    .setCategory(categoryService.findByName("Birds"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616677289/initPicture/ptici/galab_jcqj89.jpg");
            this.productRepository.save(productEntity);
            productEntity = new ProductEntity()
                    .setName("Mandoliya")
                    .setBirthDate(LocalDate.of(2020,12,12))
                    .setPrice(BigDecimal.valueOf(122.5))
                    .setGender(GenderEnum.FEMALE)
                    .setBreed("Kanarche")
                    .setCategory(categoryService.findByName("Birds"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616677290/initPicture/ptici/kanarche_cs6whw.jpg");
            this.productRepository.save(productEntity);
            productEntity = new ProductEntity()
                    .setName("Vasko")
                    .setBirthDate(LocalDate.of(2020,12,12))
                    .setPrice(BigDecimal.valueOf(122.5))
                    .setGender(GenderEnum.MALES)
                    .setBreed("Papagal Ara")
                    .setCategory(categoryService.findByName("Birds"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616677290/initPicture/ptici/ara_g6ebhn.jpg");
            this.productRepository.save(productEntity);

            // grizachi
            productEntity = new ProductEntity()
                    .setName("Petya")
                    .setBirthDate(LocalDate.of(2020,12,12))
                    .setPrice(BigDecimal.valueOf(122.5))
                    .setGender(GenderEnum.FEMALE)
                    .setBreed("Zaiche")
                    .setCategory(categoryService.findByName("Animalias"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616677508/initPicture/animalias/zaiche2_all6wk.jpg");
            this.productRepository.save(productEntity);
            productEntity = new ProductEntity()
                    .setName("Slavi")
                    .setBirthDate(LocalDate.of(2020,12,12))
                    .setPrice(BigDecimal.valueOf(122.5))
                    .setGender(GenderEnum.MALES)
                    .setBreed("Morski svinche")
                    .setCategory(categoryService.findByName("Animalias"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616677508/initPicture/animalias/morski_svinche_lfxbcq.jpg");
            this.productRepository.save(productEntity);
            productEntity = new ProductEntity()
                    .setName("Niki")
                    .setBirthDate(LocalDate.of(2020,12,12))
                    .setPrice(BigDecimal.valueOf(122.5))
                    .setGender(GenderEnum.MALES)
                    .setBreed("Zaiche")
                    .setCategory(categoryService.findByName("Animalias"))
                    .setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616677508/initPicture/animalias/zaiche1_x1zre0.jpg");
            this.productRepository.save(productEntity);


        }//end of if count
    }



}
