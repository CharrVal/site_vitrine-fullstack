import { Product} from '../../products/interface/product'

export interface Category {
    id:number;
    name?:string;
    products?: Product[];
}
